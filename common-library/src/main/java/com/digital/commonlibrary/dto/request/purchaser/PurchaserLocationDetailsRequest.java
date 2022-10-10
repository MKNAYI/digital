package com.digital.commonlibrary.dto.request.purchaser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserLocationDetailsRequest {
    private Double latitude;
    private Double longitude;
    private Integer radius;
    private String searchValue ="";
}
