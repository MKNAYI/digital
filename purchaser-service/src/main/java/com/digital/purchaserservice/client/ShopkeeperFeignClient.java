package com.digital.purchaserservice.client;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="shopkeeper-feign", url = "localhost:8081/shopkeeper-service/api/v1/shopkeeper")
public interface ShopkeeperFeignClient {
    @PostMapping("/locations")
    public PageImplDetailsResponse<LocationDetailsResponse> findNearestShopkeeperLocationBySearchCriteria(@RequestBody PurchaserLocationDetailsRequest locationDetailsRequest, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                                          @RequestParam(value = "size", defaultValue = "20") int size);

    @GetMapping("/{shopkeeper-id}/location/{location-id}/menus")
    LocationDetailsResponse findMenuDetailsByNearestShopkeeperAndLocation(@PathVariable("shopkeeper-id") Long shopkeeperId, @PathVariable("location-id") Long locationId);

    @PostMapping("/purchaser-order")
    PurchaserOrderDetailsResponse placePurchaserOrder(@RequestBody PurchaserOrderDetailsRequest orderDetailsRequest);

    @GetMapping("/purchaser-order/{purchaser-id}/order/{order-id}")
    PurchaserOrderDetailsResponse getPurchaserOrderDetails(@PathVariable("purchaser-id") Long purchaserId, @PathVariable("order-id") Long orderId);
}
