package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.dto.response.shopkeeper.LocationDetailsResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name ="location_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetails extends AuditDetails {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopkeeper")
    private ShopkeeperDetails shopkeeper;

    private String addressLine1;

    private String addressLine2;

    private String postcode;

    private String city;

    private String country;

    private String contactNumber;

    private Double latitude;

    private Double longitude;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "location_menu_details", joinColumns = @JoinColumn(name = "location_id"), inverseJoinColumns = @JoinColumn(name = "menu_id "))
    @ToString.Exclude
    @JsonIgnore
    private Set<MenuDetails> menu = new HashSet<MenuDetails>();

    public static LocationDetailsResponse build(LocationDetails location, Boolean isRequiredMenu){
        return LocationDetailsResponse.builder()
                .id(location.getId())
                .shopkeeperId(location.getShopkeeper().getId())
                .shopkeeperName(location.getShopkeeper().getName())
                .addressLine1(location.getAddressLine1())
                .addressLine2(location.getAddressLine2())
                .city(location.getCity())
                .postcode(location.getPostcode())
                .country(location.getCountry())
                .contactNumber(location.getContactNumber())
                .menusDetails(isRequiredMenu == Boolean.TRUE ? MenuDetails.build(location.getMenu()) : null)
                .build();
    }
    public static List<LocationDetailsResponse> build(List<LocationDetails> locations){
        if(CollectionUtils.isEmpty(locations) == false){
            return locations.stream().map(location -> LocationDetails.build(location, Boolean.FALSE)).collect(Collectors.toList());
        }
        return new ArrayList<LocationDetailsResponse>();
    }
}
