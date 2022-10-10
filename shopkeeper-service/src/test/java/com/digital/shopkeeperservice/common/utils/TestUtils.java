package com.digital.shopkeeperservice.common.utils;

import com.digital.commonlibrary.dto.request.purchaser.PurchaserLocationDetailsRequest;
import com.digital.commonlibrary.dto.request.purchaser.PurchaserOrderDetailsRequest;
import com.digital.commonlibrary.dto.response.PageImplDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderDetailsResponse;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.digital.commonlibrary.enums.OrderStatus;
import com.digital.shopkeeperservice.data.entity.LocationDetails;
import com.digital.shopkeeperservice.data.entity.ShopkeeperDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

public class TestUtils {
    public static PurchaserLocationDetailsRequest getPurchaserLocationDTO(){
        return PurchaserLocationDetailsRequest.builder()
                .latitude(51.449943)
                .longitude(-0.9757451)
                .radius(5)
                .searchValue("")
                .build();
    }

    public static PageImplDetailsResponse<LocationDetailsResponse> getLocationDetails(){
        PageImplDetailsResponse<LocationDetailsResponse> detailsResponse = PageImplDetailsResponse.<LocationDetailsResponse>builder()
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

        return detailsResponse;
    }

    public static PurchaserOrderDetailsRequest getPurchaserOrderDetailsRequest(){
        return PurchaserOrderDetailsRequest.builder()
                .purchaserId(1L)
                .shopkeeperId(1L)
                .locationId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-09-16T13:08:42"))
                .selectedMenuItemAndQuantityMap(Map.of(1L,1))
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

    public static LocationDetailsResponse getLocationDetailsResponse(){
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
                .menusDetails(null)
                .build();
    }

    public static Page<LocationDetails> getPageLocationList(){
        Pageable pageable= PageRequest.of(0,5);
        LocationDetails location = getLocation();
        return new PageImpl<LocationDetails>(Collections.singletonList(location),pageable,1);
    }

    public static LocationDetails getLocation() {
        LocationDetails location = LocationDetails.builder()
                .addressLine1("city plaza")
                .addressLine2("rani garden")
                .city("Patan")
                .postcode("123456")
                .country("INDIA")
                .contactNumber("+919822989891")
                .build();

        ShopkeeperDetails shopkeeperDetails = ShopkeeperDetails.builder().build();
        shopkeeperDetails.setId(1L);
        shopkeeperDetails.setName("Nescafe Hub");
        location.setId(1L);
        location.setShopkeeper(shopkeeperDetails);
        location.setMenu(null);
        return location;
    }
}
