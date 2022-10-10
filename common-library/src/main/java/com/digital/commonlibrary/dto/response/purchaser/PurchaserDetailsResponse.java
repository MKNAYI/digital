package com.digital.commonlibrary.dto.response.purchaser;

import com.digital.commonlibrary.enums.AddressType;
import com.digital.commonlibrary.enums.PurchaserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserDetailsResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postcode;

    private String country;

    private PurchaserStatus status;

    private AddressType addressType;
}
