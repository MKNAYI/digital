package com.digital.shopkeeperservice.data.entity;

import com.digital.commonlibrary.data.entity.AuditDetails;
import com.digital.commonlibrary.enums.ShopkeeperStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="shopkeeper_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopkeeperDetails extends AuditDetails {
    private String name;

    @Enumerated(EnumType.STRING)
    private ShopkeeperStatus status = ShopkeeperStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopkeeper")
    @OrderBy("createdAt DESC")
    private Set<LocationDetails> locations = new HashSet<LocationDetails>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopkeeper")
    @OrderBy("createdAt DESC")
    private Set<MenuDetails> menuDetails = new HashSet<MenuDetails>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shopkeeper")
    @OrderBy("createdAt DESC")
    private Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
}
