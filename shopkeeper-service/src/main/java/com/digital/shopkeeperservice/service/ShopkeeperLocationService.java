package com.digital.shopkeeperservice.service;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.shopkeeperservice.data.entity.LocationDetails;
import com.digital.shopkeeperservice.data.repository.LocationDetailsRepository;
import com.digital.commonlibrary.enums.ErrorCode;
import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.commonlibrary.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

@Service
@Slf4j
public class ShopkeeperLocationService {

    @Autowired
    private LocationDetailsRepository locationDetailsRepository;

    public PageImplDetailsResponse<LocationDetailsResponse> findShopkeeperLocationMenusBySearchCriteria(PurchaserLocationDetailsRequest locationDetailsRequest, PageRequest pageable){
        Page<LocationDetails> locationList = locationDetailsRepository.findShopkeeperLocationMenusBySearchCriteria(locationDetailsRequest.getLatitude(), locationDetailsRequest.getLongitude(), locationDetailsRequest.getRadius(), locationDetailsRequest.getSearchValue(), pageable);

        return PageImplDetailsResponse.<LocationDetailsResponse>builder()
                .items(CollectionUtils.isEmpty(locationList.getContent()) == false ? LocationDetails.build(locationList.getContent()) : new ArrayList<>())
                .totalElements(locationList.getTotalElements())
                .pageNumber(locationList.getNumber())
                .size(locationList.getSize())
                .totalPages(locationList.getTotalPages())
                .build();
    }

    public LocationDetailsResponse getShopkeeperLocationMenusById(Long shopkeeperId, Long locationId) {
        if(shopkeeperId == null || locationId == null){
            throw new CustomException("Shopkeeper-id and location-id is required.", ErrorCode.VALIDATION_ERROR, HttpStatus.BAD_REQUEST);
        }
        LocationDetails location = locationDetailsRepository.findByIdAndShopkeeperIdAndAndShopkeeperStatus(locationId, shopkeeperId, ShopkeeperStatus.ACTIVE);

        if(location == null){
            throw new ResourceNotFoundException(LocationDetails.class,
                    String.format("Location not found with given location: %s and Shopkeeper: %s", locationId, shopkeeperId),
                    ErrorCode.LOCATION_NOT_FOUND);
        }

        return LocationDetails.build(location, Boolean.TRUE);
    }
}
