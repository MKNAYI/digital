package com.digital.shopkeeperservice.service;

import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.shopkeeperservice.common.utils.TestUtils;
import com.digital.shopkeeperservice.data.entity.LocationDetails;
import com.digital.shopkeeperservice.data.repository.LocationDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopkeeperDetailsLocationServiceTest {

    @InjectMocks
    private ShopkeeperLocationService shopkeeperLocationService;

    @Mock
    private LocationDetailsRepository locationDetailsRepository;


    @Test
    public void getShopkeeperLocationMenusBySearchCriteriaSuccess(){
        PageImplDetailsResponse<LocationDetailsResponse> pageListDTO = TestUtils.getLocationDetails();
        Page<LocationDetails> pageLocationList = TestUtils.getPageLocationList();
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(locationDetailsRepository.findShopkeeperLocationMenusBySearchCriteria(51.449943, -0.9757451, 5, "", pageRequest)).thenReturn(pageLocationList);
        PageImplDetailsResponse<LocationDetailsResponse>  result =  shopkeeperLocationService.findShopkeeperLocationMenusBySearchCriteria(TestUtils.getPurchaserLocationDTO(),pageRequest);
        Assertions.assertEquals(pageListDTO.getItems(), result.getItems());
    }

    @Test
    public void getShopkeeperLocationMenusBySearchCriteriaShouldThrow(){
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(locationDetailsRepository.findShopkeeperLocationMenusBySearchCriteria(51.449943, -0.9757451, 5, "", pageRequest)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> shopkeeperLocationService.findShopkeeperLocationMenusBySearchCriteria(TestUtils.getPurchaserLocationDTO(),pageRequest));
    }

    @Test
    public void getMenuByNearestShopkeeperAndLocationShouldSuccess(){
        LocationDetailsResponse  locationDTO = TestUtils.getLocationDetailsResponse();
        LocationDetails location = TestUtils.getLocation();
        when(locationDetailsRepository.findByIdAndShopkeeperIdAndAndShopkeeperStatus(1L, 1L, ShopkeeperStatus.ACTIVE)).thenReturn(location);
        LocationDetailsResponse  result =  shopkeeperLocationService.getShopkeeperLocationMenusById(1L, 1L);
        Assertions.assertEquals(locationDTO.getId(), result.getId());
    }

    @Test
    public void getMenuByNearestShopkeeperAndLocationShouldThrow(){
        when(locationDetailsRepository.findByIdAndShopkeeperIdAndAndShopkeeperStatus(1L,1L, ShopkeeperStatus.ACTIVE)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> shopkeeperLocationService.getShopkeeperLocationMenusById(1L,1L));
    }
}
