package com.digital.shopkeeperservice.data.repository;

import com.digital.shopkeeperservice.data.entity.MenuDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDetailsRepository extends JpaRepository<MenuDetails, Long> {
}
