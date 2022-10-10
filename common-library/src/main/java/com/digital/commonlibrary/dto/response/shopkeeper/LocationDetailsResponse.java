package com.digital.commonlibrary.dto.response.shopkeeper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetailsResponse {
    private Long id;

    private Long shopkeeperId;

    private String shopkeeperName;

    private String addressLine1;

    private String addressLine2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private List<MenuDetailsResponse> menusDetails;
}
