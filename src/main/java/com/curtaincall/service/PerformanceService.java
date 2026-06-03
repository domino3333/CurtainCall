package com.curtaincall.service;

import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailViewResponse;
import com.curtaincall.dto.performance.list.PerformanceListResponse;

public interface PerformanceService {

    PerformanceListResponse getPerformances(int page, int size, String keyword, String realm);

    PerformanceDetailViewResponse getPerformance(Long seq);

    AreaPerformanceResponse getRegionalCultureInfo();

    PerformanceDetailResponse getPerformanceDetail(Long seq);

    
}
