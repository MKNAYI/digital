package com.digital.purchaserservice.controller;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.purchaserservice.service.PurchaserLocationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/purchaser")
public class PurchaserLocationController {

    @Autowired
    private PurchaserLocationDetailsService purchaserLocationService;

    @PostMapping("/nearest-shopkeeper/locations")
    public ResponseEntity<PageImplDetailsResponse<LocationDetailsResponse>> findNearestShopkeeperLocationBySearchCriteria(@RequestBody PurchaserLocationDetailsRequest locationDetailsRequest,
                                                                                                                          @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                                                                                          @RequestParam(value = "size", defaultValue = "20") int size ){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"id"));
        return new ResponseEntity(purchaserLocationService.findNearestShopkeeperLocationBySearchCriteria(locationDetailsRequest, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/nearest-shopkeeper/{shopkeeper-id}/location/{location-id}/menus")
    public ResponseEntity<LocationDetailsResponse> findMenuDetailsByNearestShopkeeperAndLocation(@PathVariable("shopkeeper-id") Long shopkeeperId, @PathVariable("location-id") Long locationId){
        return new ResponseEntity(purchaserLocationService.findMenuDetailsByNearestShopkeeperAndLocation(shopkeeperId, locationId), HttpStatus.OK);
    }
}
