package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.shopkeeper.MenuDetailsResponse;
import com.digital.commonlibrary.enums.MenuStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="menu_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDetails extends AuditDetails {

    private String name;

    private String startTime;

    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopkeeper")
    private ShopkeeperDetails shopkeeper;

    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @ManyToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @OrderBy("id ASC")
    private Set<LocationDetails> locations = new HashSet<>();

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @OrderBy("id ASC")
    private Set<MenuItemDetails> menuItemDetails = new HashSet<MenuItemDetails>();

    public static MenuDetailsResponse build(MenuDetails menuDetails) {
        return MenuDetailsResponse.builder()
                .id(menuDetails.getId())
                .name(menuDetails.getName())
                .startTime(menuDetails.getStartTime())
                .endTime(menuDetails.getEndTime())
                .status(menuDetails.getStatus())
                .menuItemDetails(MenuItemDetails.build(menuDetails.getMenuItemDetails()))
                .build();
    }

    public static List<MenuDetailsResponse> build(Set<MenuDetails> menuDetails) {
        if(CollectionUtils.isEmpty(menuDetails) == false){
            return menuDetails.stream().map(menu -> MenuDetails.build(menu)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
