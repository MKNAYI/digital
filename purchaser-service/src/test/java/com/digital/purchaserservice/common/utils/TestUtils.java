package com.digital.purchaserservice.common.utils;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.commonlibrary.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Collections;

public class TestUtils {

    public static PurchaserLocationDetailsRequest getPurchaserLocationDetailsRequest(){
        return PurchaserLocationDetailsRequest.builder()
                .latitude(51.449943)
                .longitude(-0.9757451)
                .radius(5)
                .searchValue("")
                .build();
    }

    public static PageImplDetailsResponse<LocationDetailsResponse> getPageableLocationDetails(){
        PageImplDetailsResponse<LocationDetailsResponse> pageListDTO = PageImplDetailsResponse.<LocationDetailsResponse>builder()
                 .items(Collections.singletonList(LocationDetailsResponse.builder()
                                 .id(1L)
                                 .shopkeeperId(1L)
                                 .shopkeeperName("Nescafe Hub")
                                 .addressLine1("city plaza")
                                 .addressLine2("rani garden")
                                 .city("Patan")
                                 .postcode("123456")
                                 .country("INDIA")
                                 .contactNumber("+919822989891")
                                 .build()))
                 .pageNumber(1)
                 .size(1)
                 .totalElements(1)
                 .build();

        return pageListDTO;
    }

    public static PurchaserOrderDetailsRequest getPurchaserOrderDetailsRequest(){
        return PurchaserOrderDetailsRequest.builder()
                .purchaserId(1L)
                .shopkeeperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-09-16T13:08:42"))
                .build();
    }

    public static PurchaserOrderDetailsResponse getPurchaserOrderDetailsResponse() {
        return PurchaserOrderDetailsResponse.builder()
                .id(1L)
                .purchaserId(1L)
                .shopkeeperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-09-16T13:08:42"))
                .orderStatus(OrderStatus.QUEUED)
                .build();
    }

    public static LocationDetailsResponse getLocationDetails(){
        return LocationDetailsResponse.builder()
                .id(1L)
                .shopkeeperId(1L)
                .shopkeeperName("Nescafe Hub")
                .addressLine1("city plaza")
                .addressLine2("rani garden")
                .city("Patan")
                .postcode("123456")
                .country("INDIA")
                .contactNumber("+919822989891")
                .build();
    }
}
