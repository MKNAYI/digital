package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.shopkeeper.PurchaserOrderItemDetailsResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="purchaser_order_item_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDetails extends AuditDetails {

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_order")
    private OrderDetails order;

    public static PurchaserOrderItemDetailsResponse build(OrderItemDetails orderItemDetails) {
        return PurchaserOrderItemDetailsResponse.builder()
                .id(orderItemDetails.getId())
                .menuItemId(orderItemDetails.getMenuItemId())
                .price(orderItemDetails.getPrice())
                .quantity(orderItemDetails.getQuantity())
                .build();
    }

    public static List<PurchaserOrderItemDetailsResponse> build(Set<OrderItemDetails> orderItemDetails) {
        if(CollectionUtils.isEmpty(orderItemDetails) == false){
            return orderItemDetails.stream().map(orderItems -> OrderItemDetails.build(orderItems)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
