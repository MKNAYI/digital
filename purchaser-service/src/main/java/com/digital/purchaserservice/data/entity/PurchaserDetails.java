package com.digital.purchaserservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.purchaser.PurchaserDetailsResponse;
import com.digital.commonlibrary.enums.AddressType;
import com.digital.commonlibrary.enums.PurchaserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name ="purchaser_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserDetails extends AuditDetails {
    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postcode;

    private String country;

    private String password;

    @Enumerated(EnumType.STRING)
    private PurchaserStatus status = PurchaserStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    public static PurchaserDetailsResponse build(PurchaserDetails purchaserDetails){
        return PurchaserDetailsResponse.builder()
                .id(purchaserDetails.getId())
                .firstName(purchaserDetails.getFirstName())
                .lastName(purchaserDetails.getLastName())
                .mobileNumber(purchaserDetails.getMobileNumber())
                .addressLine1(purchaserDetails.getAddressLine1())
                .addressLine2(purchaserDetails.getAddressLine2())
                .city(purchaserDetails.getCity())
                .postcode(purchaserDetails.getPostcode())
                .country(purchaserDetails.getCountry())
                .status(purchaserDetails.getStatus())
                .addressType(purchaserDetails.getAddressType())
                .build();
    }
}