package com.curtaincall.dto.performance.list;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PerformanceListResponse {

    private List<PerformanceListItemResponse> performances;
    private List<String> realms;
    private Integer page;
    private Integer size;
    private Integer totalCount;
    private Integer totalPages;
}
