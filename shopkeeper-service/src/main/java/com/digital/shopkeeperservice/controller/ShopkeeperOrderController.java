package com.digital.shopkeeperservice.controller;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.shopkeeperservice.service.ShopkeeperOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shopkeeper")
public class ShopkeeperOrderController {

    @Autowired
    private ShopkeeperOrderService shopkeeperOrderService;

    @PostMapping("/purchaser-order")
    public ResponseEntity<PurchaserOrderDetailsResponse> placePurchaserOrder(@RequestBody PurchaserOrderDetailsRequest orderDetailsRequest){
        return new ResponseEntity(shopkeeperOrderService.placePurchaserOrder(orderDetailsRequest), HttpStatus.OK);
    }

    @GetMapping("/purchaser-order/{purchaser-id}/order/{order-id}")
    public ResponseEntity<PurchaserOrderDetailsResponse> getPurchaserOrderDetails(@PathVariable("purchaser-id") Long purchaserId, @PathVariable("order-id") Long orderId){
        return new ResponseEntity(shopkeeperOrderService.getPurchaserOrderDetails(purchaserId, orderId), HttpStatus.OK);
    }
}
