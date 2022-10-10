package com.digital.commonlibrary.dto.response.shopkeeper;

import com.digital.commonlibrary.enums.MenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDetailsResponse {
    private Long id;

    private String name;

    private String startTime;

    private String endTime;

    private MenuStatus status;

    private List<MenuItemDetailsResponse> menuItemDetails;
}
