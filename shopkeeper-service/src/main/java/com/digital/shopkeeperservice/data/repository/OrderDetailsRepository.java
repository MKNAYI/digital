package com.digital.shopkeeperservice.data.repository;

import com.digital.shopkeeperservice.data.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    OrderDetails findByPurchaserIdAndId(Long purchaserId, Long orderId);
}
