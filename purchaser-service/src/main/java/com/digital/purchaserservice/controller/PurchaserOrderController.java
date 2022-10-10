package com.digital.purchaserservice.controller;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.purchaserservice.service.PurchaserOrderDetailsService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/api/v1/purchaser")
public class PurchaserOrderController {

    @Autowired
    private PurchaserOrderDetailsService purchaserOrderDetailsService;

    @PostMapping("/{purchaser-id}/order")
    public ResponseEntity<PurchaserOrderDetailsResponse> placePurchaserOrder(@RequestBody PurchaserOrderDetailsRequest orderDetailsRequest){
        return new ResponseEntity(purchaserOrderDetailsService.placePurchaserOrder(orderDetailsRequest), HttpStatus.OK);
    }

    @GetMapping("/{purchaser-id}/order/{order-id}")
    public ResponseEntity<PurchaserOrderDetailsResponse> getPurchaserOrderDetails(@PathVariable("purchaser-id") Long purchaserId, @PathVariable("order-id") Long orderId){
        return new ResponseEntity(purchaserOrderDetailsService.getPurchaserOrderDetails(purchaserId, orderId), HttpStatus.OK);
    }
}
