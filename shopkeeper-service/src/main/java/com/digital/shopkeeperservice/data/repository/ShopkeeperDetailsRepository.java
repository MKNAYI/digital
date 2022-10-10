package com.digital.shopkeeperservice.data.repository;

import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.shopkeeperservice.data.entity.ShopkeeperDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopkeeperDetailsRepository extends JpaRepository<ShopkeeperDetails, Long> {
    ShopkeeperDetails findByIdAndStatus(Long shopkeeperId, ShopkeeperStatus active);
}
