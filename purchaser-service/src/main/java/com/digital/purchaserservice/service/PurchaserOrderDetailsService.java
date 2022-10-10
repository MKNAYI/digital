package com.digital.purchaserservice.service;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.enums.ErrorCode;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.purchaserservice.client.ShopkeeperFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PurchaserOrderDetailsService {

    @Autowired
    public ShopkeeperFeignClient shopkeeperFeignClient;

    public PurchaserOrderDetailsResponse placePurchaserOrder(PurchaserOrderDetailsRequest orderDetailsRequest) {
        try{
            PurchaserOrderDetailsResponse orderDetailsResponse = shopkeeperFeignClient.placePurchaserOrder(orderDetailsRequest);
            log.info("Purchaser order placed successfully, purchaser : {}, order : {}, order-status : {}", orderDetailsResponse.getPurchaserId(), orderDetailsResponse.getId(), orderDetailsResponse.getOrderStatus());
            return orderDetailsResponse;
        }catch (Exception e){
            log.error("Error while placing the purchaser-order, Error : {}",e.getMessage());
            throw new CustomException(String.format("Error while placing the purchaser-order, Error : %s",e.getMessage()), ErrorCode.SHOPKEEPER_FEIGN_CLIENT_ERROR, HttpStatus.NO_CONTENT);
        }
    }

    public PurchaserOrderDetailsResponse getPurchaserOrderDetails(Long purchaserId, Long orderId) {
        try{
            return shopkeeperFeignClient.getPurchaserOrderDetails(purchaserId, orderId);
        }catch (Exception e){
            log.error("Error while getting the purchaser-order : purchaser : {}, order : {}, Error : {}", purchaserId, orderId, e.getMessage());
            throw new CustomException(String.format("Error while getting the purchaser-order : purchaser : %s, order : %s, Error : %s", purchaserId, orderId, e.getMessage()), ErrorCode.SHOPKEEPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
