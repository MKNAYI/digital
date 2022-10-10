package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="purchaser_order_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails extends AuditDetails {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopkeeper")
    private ShopkeeperDetails shopkeeper;

    private Long locationId;

    private Long menuId;

    private Long purchaserId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime pickUpTime;

    private LocalDateTime orderPreparingTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private Set<OrderItemDetails> orderItems = new HashSet<OrderItemDetails>();

    public static PurchaserOrderDetailsResponse build(OrderDetails orderDetails) {
        return PurchaserOrderDetailsResponse.builder()
                .id(orderDetails.getId())
                .purchaserId(orderDetails.getPurchaserId())
                .shopkeeperId(orderDetails.getShopkeeper().getId())
                .locationId(orderDetails.getLocationId())
                .menuId(orderDetails.getMenuId())
                .totalAmount(orderDetails.getTotal())
                .orderStatus(orderDetails.getStatus())
                .orderItemsDetails(OrderItemDetails.build(orderDetails.getOrderItems()))
                .pickUpTime(orderDetails.getPickUpTime())
                .orderPreparingTime(orderDetails.getOrderPreparingTime())
                .build();
    }

    public static List<PurchaserOrderDetailsResponse> build(Set<OrderDetails> orderDetails) {
        if(CollectionUtils.isEmpty(orderDetails) == false){
            return orderDetails.stream().map(order -> OrderDetails.build(order)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
