package com.digital.shopkeeperservice.data.repository;

import com.digital.commonlibrary.enums.ShopkeeperStatus;
import com.digital.shopkeeperservice.data.entity.LocationDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailsRepository extends JpaRepository<LocationDetails, Long> {

    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(?1)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(?2)) + sin(radians(?1)) * sin(radians(l.latitude))))";

    @Query(value = "SELECT * FROM location_details lo WHERE lo.id IN ( " +
            "SELECT  l.id FROM location_details AS l JOIN shopkeeper_details AS s ON ( s.id = l.shopkeeper) " +
            "JOIN location_menu_details AS lm ON (lm.location_id = l.id) " +
            "JOIN menu_details AS mu ON ( lm.menu_id = mu.id) " +
            "JOIN menu_item_details AS mi ON (mi.menu = mu.id) " +
            "WHERE ("+HAVERSINE_FORMULA+" < ?3) " +
            "AND ((?4 is null) OR (s.name like CONCAT('%',?4,'%') OR mi.category like CONCAT('%',?4,'%') OR (mi.sub_category like CONCAT('%',?4,'%') OR mi.item_name like CONCAT('%',?4,'%')))))",nativeQuery = true)
    Page<LocationDetails> findShopkeeperLocationMenusBySearchCriteria(Double latitude, Double longitude, Integer radius, String searchValue, PageRequest pageable);

    LocationDetails findByIdAndShopkeeperIdAndAndShopkeeperStatus(Long locationId, Long shopkeeperId, ShopkeeperStatus active);
}
