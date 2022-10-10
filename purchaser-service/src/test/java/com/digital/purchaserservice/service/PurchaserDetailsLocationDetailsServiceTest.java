package com.digital.purchaserservice.service;

import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.commonlibrary.exception.CustomException;
import com.digital.purchaserservice.client.ShopkeeperFeignClient;
import com.digital.purchaserservice.common.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaserDetailsLocationDetailsServiceTest {

    @InjectMocks
    private PurchaserLocationDetailsService purchaserLocationService;

    @Mock
    private ShopkeeperFeignClient shopkeeperFeignClient;

    @Test
    public void getNearestShopkeeperLocationBySearchCriteriaShouldSuccess(){
        PageImplDetailsResponse<LocationDetailsResponse> pageListDTO = TestUtils.getPageableLocationDetails();

        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(shopkeeperFeignClient.findNearestShopkeeperLocationBySearchCriteria(TestUtils.getPurchaserLocationDetailsRequest(),1,1)).thenReturn(pageListDTO);
        PageImplDetailsResponse<LocationDetailsResponse>  result =  purchaserLocationService.findNearestShopkeeperLocationBySearchCriteria(TestUtils.getPurchaserLocationDetailsRequest(),pageRequest);
        Assertions.assertEquals(1,result.getSize());
    }

    @Test
    public void getNearestShopkeeperLocationBySearchCriteriaShouldReturnNotEmpty(){
        PageImplDetailsResponse<LocationDetailsResponse> pageListDTO = TestUtils.getPageableLocationDetails();
        PageRequest pageRequest = PageRequest.of(1, 1, Sort.by(Sort.Direction.DESC,"id"));
        when(shopkeeperFeignClient.findNearestShopkeeperLocationBySearchCriteria(TestUtils.getPurchaserLocationDetailsRequest(),1,1)).thenReturn(pageListDTO);
        PageImplDetailsResponse<LocationDetailsResponse>  result =  purchaserLocationService.findNearestShopkeeperLocationBySearchCriteria(TestUtils.getPurchaserLocationDetailsRequest(),pageRequest);
        Assertions.assertNotNull(result.getItems());
    }

    @Test
    public void getMenuByNearestShopkeeperAndLocationShouldSuccess(){
        LocationDetailsResponse  locationDTO = TestUtils.getLocationDetails();
        when(shopkeeperFeignClient.findMenuDetailsByNearestShopkeeperAndLocation(1L, 1L)).thenReturn(locationDTO);
        LocationDetailsResponse  result =  purchaserLocationService.findMenuDetailsByNearestShopkeeperAndLocation(1L, 1L);
        Assertions.assertEquals(locationDTO, result);
    }

    @Test
    public void getMenuByNearestShopkeeperAndLocationShouldThrow(){
        when(shopkeeperFeignClient.findMenuDetailsByNearestShopkeeperAndLocation(1L,1L)).thenThrow(CustomException.class);
        Assertions.assertThrows(CustomException.class,() -> purchaserLocationService.findMenuDetailsByNearestShopkeeperAndLocation(1L,1L));
    }
}
