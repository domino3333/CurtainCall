package com.curtaincall.service.Impl;

import com.curtaincall.client.PerformanceApiClient;
import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceApiClient performanceApiClient;


    @Override
    public AreaPerformanceResponse getRegionalCultureInfo() {
        return performanceApiClient.fetchRegionalCultureInfo();
    }

    @Override
    public PerformanceDetailResponse getPerformanceDetail(Long seq) {

        return performanceApiClient.fetchPerformanceDetail(seq);
    }
}
