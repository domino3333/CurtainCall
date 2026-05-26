package com.curtaincall.service.Impl;

import com.curtaincall.client.PerformanceApiClient;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceApiClient performanceApiClient;


    @Override
    public String getPerformanceApiTest() {
        return performanceApiClient.fetchRegionalCultureInfo();
    }
}
