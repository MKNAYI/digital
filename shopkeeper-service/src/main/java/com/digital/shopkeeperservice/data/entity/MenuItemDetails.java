package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.shopkeeper.MenuItemDetailsResponse;
import com.digital.commonlibrary.enums.OrderItemCategory;
import com.digital.commonlibrary.enums.OrderItemSubCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name ="menu_item_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDetails extends AuditDetails {

    @Enumerated(EnumType.STRING)
    private OrderItemCategory category;

    @Enumerated(EnumType.STRING)
    private OrderItemSubCategory subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;

    private Integer preparingDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu")
    @JsonIgnore
    private MenuDetails menu;

    public static MenuItemDetailsResponse build(MenuItemDetails menuItemDetails) {
        return MenuItemDetailsResponse.builder()
                .id(menuItemDetails.getId())
                .category(menuItemDetails.getCategory())
                .subCategory(menuItemDetails.getSubCategory())
                .itemName(menuItemDetails.getItemName())
                .price(menuItemDetails.getPrice())
                .quantity(menuItemDetails.getQuantity())
                .build();
    }

    public static List<MenuItemDetailsResponse> build(Set<MenuItemDetails> menuItemDetails) {
        if(CollectionUtils.isEmpty(menuItemDetails) == false){
            return menuItemDetails.stream().map(menuItems -> MenuItemDetails.build(menuItems)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
