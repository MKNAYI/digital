package com.digital.commonlibrary.dto.response.shopkeeper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserOrderItemDetailsResponse {
    private Long id;

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;
}
