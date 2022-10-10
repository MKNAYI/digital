package com.digital.commonlibrary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageImplDetailsResponse<E> {
    private List<E> items;

    private long totalElements;

    private int totalPages;

    private int size;

    private int pageNumber;
}
