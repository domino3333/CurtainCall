package com.curtaincall.service;

import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;

public interface PerformanceService {

    AreaPerformanceResponse getRegionalCultureInfo();

    PerformanceDetailResponse getPerformanceDetail(Long seq);

    
}
