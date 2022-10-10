package com.digital.commonlibrary.dto.response.shopkeeper;

import com.digital.commonlibrary.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaserOrderDetailsResponse {
    private Long id;

    private Long purchaserId;

    private Long shopkeeperId;

    private Long locationId;

    private Long menuId;

    private BigDecimal totalAmount;

    private List<PurchaserOrderItemDetailsResponse> orderItemsDetails;

    private OrderStatus orderStatus;

    private LocalDateTime pickUpTime;

    private LocalDateTime orderPreparingTime;
}
