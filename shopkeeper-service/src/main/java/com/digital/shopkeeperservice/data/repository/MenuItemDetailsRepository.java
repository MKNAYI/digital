package com.digital.shopkeeperservice.data.repository;

import com.digital.commonlibrary.enums.MenuStatus;
import com.digital.shopkeeperservice.data.entity.MenuItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemDetailsRepository extends JpaRepository<MenuItemDetails, Long> {
    List<MenuItemDetails> findAllByMenuShopkeeperIdAndMenuStatus(Long shopkeeperId, MenuStatus active);
}
