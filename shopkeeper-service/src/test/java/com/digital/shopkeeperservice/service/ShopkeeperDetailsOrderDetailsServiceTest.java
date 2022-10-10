package com.digital.shopkeeperservice.service;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.shopkeeperservice.common.utils.TestUtils;
import com.digital.shopkeeperservice.data.repository.ShopkeeperDetailsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShopkeeperDetailsOrderDetailsServiceTest {

    @InjectMocks
    private ShopkeeperOrderService shopkeeperOrderService;

    @Mock
    private ShopkeeperDetailsRepository shopkeeperDetailsRepository;



    @Test
    public void placePurchaserOrderShouldSuccess(){
        PurchaserOrderDetailsRequest orderDetailsRequest = TestUtils.getPurchaserOrderDetailsRequest();
        PurchaserOrderDetailsResponse purchaserOrderDetailsResponse = TestUtils.getPurchaserOrderDetailsResponse();
        when(shopkeeperDetailsRepository.findByIdAndStatus(anyLong(), ShopkeeperStatus.ACTIVE)).thenReturn(any());
        when(shopkeeperOrderService.placePurchaserOrder(orderDetailsRequest)).thenReturn(purchaserOrderDetailsResponse);
        PurchaserOrderDetailsResponse result =  shopkeeperOrderService.placePurchaserOrder(orderDetailsRequest);
        Assertions.assertEquals(purchaserOrderDetailsResponse, result);
    }

}
