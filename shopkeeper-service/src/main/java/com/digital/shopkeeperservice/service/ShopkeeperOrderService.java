package com.digital.shopkeeperservice.service;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.shopkeeperservice.data.entity.MenuItemDetails;
import com.digital.shopkeeperservice.data.entity.ShopkeeperDetails;
import com.digital.shopkeeperservice.data.entity.OrderDetails;
import com.digital.shopkeeperservice.data.entity.OrderItemDetails;
import com.digital.shopkeeperservice.data.repository.ShopkeeperDetailsRepository;
import com.digital.shopkeeperservice.data.repository.OrderDetailsRepository;
import com.digital.shopkeeperservice.data.repository.MenuItemDetailsRepository;
import com.digital.commonlibrary.enums.ErrorCode;
import com.digital.commonlibrary.enums.MenuStatus;
import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.commonlibrary.enums.OrderStatus;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.commonlibrary.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopkeeperOrderService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ShopkeeperDetailsRepository shopkeeperRepository;

    @Autowired
    private MenuItemDetailsRepository menuItemDetailsRepository;

    public PurchaserOrderDetailsResponse placePurchaserOrder(PurchaserOrderDetailsRequest orderDetailsRequest) {
        if(orderDetailsRequest.getSelectedMenuItemAndQuantityMap().size() == 0){
            log.error("An order has at least one menu item.");
            throw new CustomException("Order must have at least one menu item.", ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        ShopkeeperDetails shopkeeperDetails = shopkeeperRepository.findByIdAndStatus(orderDetailsRequest.getShopkeeperId(), ShopkeeperStatus.ACTIVE);
        if(shopkeeperDetails == null){
            log.error("Shopkeeper not found with given id : {}", orderDetailsRequest.getShopkeeperId());
            throw new ResourceNotFoundException(ShopkeeperDetails.class, String.format("Shopkeeper not found with given id : %s", orderDetailsRequest.getShopkeeperId()), ErrorCode.SHOPKEEPER_NOT_FOUND);
        }

        List<MenuItemDetails> menuItemDetails = getMenuItemDetails(shopkeeperDetails.getId());

        Map<Long, BigDecimal> menuItemPriceDetailsMap = getShopkeeperMenuItemPriceDetails(menuItemDetails);
        Map<Long, Integer> menuItemMakingDurationMap = getShopkeeperMenuItemPreparingDuration(menuItemDetails);

        preValidation(orderDetailsRequest, shopkeeperDetails, menuItemPriceDetailsMap);

        OrderDetails orderDetails = mapToOrderDetails(orderDetailsRequest, shopkeeperDetails, menuItemPriceDetailsMap, menuItemMakingDurationMap);
        orderDetailsRepository.save(orderDetails);
        log.info("Purchaser order is placed successfully! Purchaser : {}, order : {}, order-status : {}", orderDetails.getPurchaserId(), orderDetails.getId(), orderDetails.getStatus());

        return OrderDetails.build(orderDetails);
    }

    private void preValidation(PurchaserOrderDetailsRequest orderDetailsRequest, ShopkeeperDetails shopkeeperDetails, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
       List<Long> locationIds = shopkeeperDetails.getLocations().stream().map(location -> location.getId()).collect(Collectors.toList());
       if(!locationIds.contains(orderDetailsRequest.getLocationId())){
           throw new CustomException(String.format("Shopkeeper location not exist with location : %s", orderDetailsRequest.getLocationId()), ErrorCode.SHOPKEEPER_LOCATION_NOT_EXIST, HttpStatus.BAD_REQUEST);
       }

        for (Map.Entry<Long, Integer> menuItemAndQuantity : orderDetailsRequest.getSelectedMenuItemAndQuantityMap().entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            if(menuItemPriceDetailsMap.get(menuItemId) == null){
                throw new CustomException(String.format("Shopkeeper menu-item not exist with menu-item : %s, shopkeeper : %s", menuItemId, shopkeeperDetails.getId()), ErrorCode.SHOPKEEPER_LOCATION_MENU_ITEM_NOT_EXIST, HttpStatus.BAD_REQUEST);
            }
        }
    }

    private List<MenuItemDetails> getMenuItemDetails(Long shopkeeperId) {
        List<MenuItemDetails> menuItemDetails = menuItemDetailsRepository.findAllByMenuShopkeeperIdAndMenuStatus(shopkeeperId, MenuStatus.ACTIVE);
        return menuItemDetails;
    }

    private Map<Long, BigDecimal> getShopkeeperMenuItemPriceDetails(List<MenuItemDetails> menuItemDetails) {
        Map<Long, BigDecimal> menuItemPriceMap = menuItemDetails.stream().collect(Collectors.toMap(MenuItemDetails::getId, MenuItemDetails:: getPrice));
        return menuItemPriceMap;
    }

    private Map<Long, Integer> getShopkeeperMenuItemPreparingDuration(List<MenuItemDetails> menuItemDetails) {
        Map<Long, Integer> menuItemPreparingDurationMap = menuItemDetails.stream().collect(Collectors.toMap(MenuItemDetails::getId, MenuItemDetails:: getPreparingDuration));
        return menuItemPreparingDurationMap;
    }

    private OrderDetails mapToOrderDetails(PurchaserOrderDetailsRequest orderDetailsRequest, ShopkeeperDetails shopkeeperDetails, Map<Long, BigDecimal> menuItemPriceDetailsMap, Map<Long, Integer> menuItemMakingDurationMap) {
        OrderDetails orderDetails = OrderDetails.builder()
                        .shopkeeper(shopkeeperDetails)
                        .locationId(orderDetailsRequest.getLocationId())
                        .menuId(orderDetailsRequest.getMenuId())
                        .purchaserId(orderDetailsRequest.getPurchaserId())
                        .status(OrderStatus.QUEUED)
                        .pickUpTime(orderDetailsRequest.getPickUpTime())
                        .build();
        Set<OrderItemDetails> orderItemDetails = mapToOrderItems(orderDetailsRequest.getSelectedMenuItemAndQuantityMap(), orderDetails, menuItemPriceDetailsMap);
        orderDetails.setOrderItems(orderItemDetails);

        BigDecimal total = orderItemDetails.stream().map(orderItem -> orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        orderDetails.setTotal(total);

        Integer totalOrderPreparingMinutes = orderDetailsRequest.getSelectedMenuItemAndQuantityMap().entrySet().stream().map(t->
                menuItemMakingDurationMap.get(t.getKey()) != null ? menuItemMakingDurationMap.get(t.getKey()) : 0)
                .reduce(0, (a, b) -> (a+b));

        orderDetails.setOrderPreparingTime(orderDetailsRequest.getPickUpTime() != null ? orderDetailsRequest.getPickUpTime().plusMinutes(totalOrderPreparingMinutes) : LocalDateTime.now().plusMinutes(totalOrderPreparingMinutes));
        return orderDetails;
    }

    private Set<OrderItemDetails> mapToOrderItems(Map<Long, Integer> selectedMenuItemAndQuantityMap, OrderDetails orderDetails, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
        Set<OrderItemDetails> orderItemDetails = new HashSet<>();
        for (Map.Entry<Long, Integer> menuItemAndQuantity : selectedMenuItemAndQuantityMap.entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            Integer quantity = menuItemAndQuantity.getValue();

            OrderItemDetails itemDetails = OrderItemDetails.builder()
                    .menuItemId(menuItemAndQuantity.getKey())
                    .quantity(quantity)
                    .order(orderDetails)
                    .price(menuItemPriceDetailsMap.get(menuItemId) != null ? menuItemPriceDetailsMap.get(menuItemId) : BigDecimal.ZERO)
                    .build();
            orderItemDetails.add(itemDetails);
        }
        return orderItemDetails;
    }

    public PurchaserOrderDetailsResponse getPurchaserOrderDetails(Long purchaserId, Long orderId) {
        OrderDetails orderDetails = orderDetailsRepository.findByPurchaserIdAndId(purchaserId, orderId);
        if(orderDetails == null){
            throw new ResourceNotFoundException(OrderDetails.class, String.format("Purchaser-order not found with given purchaser: %s, order: %s", purchaserId, orderId), ErrorCode.PURCHASER_ORDER_NOT_FOUND);
        }
        return OrderDetails.build(orderDetails);
    }
}
