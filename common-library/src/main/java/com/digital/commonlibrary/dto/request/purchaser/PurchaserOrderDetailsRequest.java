package com.digital.commonlibrary.dto.request.purchaser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserOrderDetailsRequest {
    private Long purchaserId;

    private Long shopkeeperId;

    private Long locationId;

    private Long menuId;

    private LocalDateTime pickUpTime;

    private Map<Long, Integer> selectedMenuItemAndQuantityMap;
}
