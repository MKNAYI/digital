package com.digital.commonlibrary.dto.response.shopkeeper;

import com.digital.commonlibrary.enums.OrderItemCategory;
import com.digital.commonlibrary.enums.OrderItemSubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDetailsResponse {
    private Long id;

    private OrderItemCategory category;

    private OrderItemSubCategory subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;
}
