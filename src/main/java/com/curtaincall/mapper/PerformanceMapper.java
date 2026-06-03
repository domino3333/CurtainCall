package com.curtaincall.mapper;

import com.curtaincall.domain.Performance;
import com.curtaincall.domain.PerformanceDetail;
import com.curtaincall.domain.PerformanceDetailView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PerformanceMapper {

    void upsertPerformance(Performance performance);

    void upsertPerformanceDetail(PerformanceDetail performanceDetail);

    int existsPerformanceDetail(Long seq);

    List<Performance> selectPerformances(
            @Param("keyword") String keyword,
            @Param("realm") String realm,
            @Param("offset") int offset,
            @Param("size") int size
    );

    int countPerformances(
            @Param("keyword") String keyword,
            @Param("realm") String realm
    );

    List<String> selectRealms();

    PerformanceDetailView selectPerformanceDetail(Long seq);
}
