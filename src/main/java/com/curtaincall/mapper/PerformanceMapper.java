package com.curtaincall.mapper;

import com.curtaincall.domain.Performance;
import com.curtaincall.domain.PerformanceDetail;

public interface PerformanceMapper {

    void upsertPerformance(Performance performance);

    void upsertPerformanceDetail(PerformanceDetail performanceDetail);

    int existsPerformanceDetail(Long seq);
}
