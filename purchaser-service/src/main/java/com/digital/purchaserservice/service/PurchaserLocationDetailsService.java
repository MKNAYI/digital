package com.digital.purchaserservice.service;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.commonlibrary.enums.ErrorCode;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.purchaserservice.client.ShopkeeperFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaserLocationDetailsService {

    @Autowired
    private ShopkeeperFeignClient shopkeeperFeignClient;

    public PageImplDetailsResponse<LocationDetailsResponse> findNearestShopkeeperLocationBySearchCriteria(PurchaserLocationDetailsRequest locationDetailsRequest, PageRequest pageRequest) {
        try{
            return shopkeeperFeignClient.findNearestShopkeeperLocationBySearchCriteria(locationDetailsRequest, pageRequest.getPageNumber(), pageRequest.getPageSize());
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest shopkeeper-location, Error: %s", e.getMessage()), ErrorCode.SHOPKEEPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }

    public LocationDetailsResponse findMenuDetailsByNearestShopkeeperAndLocation(Long shopkeeperId, Long locationId) {
        try{
            return shopkeeperFeignClient.findMenuDetailsByNearestShopkeeperAndLocation(shopkeeperId, locationId);
        }catch (Exception e){
            throw new CustomException(String.format("Error while getting the nearest shopkeeper-location-menu details, Error: %s", e.getMessage()), ErrorCode.SHOPKEEPER_FEIGN_CLIENT_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
