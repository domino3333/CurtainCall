package com.curtaincall.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PerformanceCollectIntegrationTest {

    private static final LocalDate COLLECT_FROM = LocalDate.of(2010, 1, 1);
    private static final LocalDate COLLECT_TO = LocalDate.now().plusYears(1);
    private static final long API_CALL_DELAY_MILLIS = 1000;
//    private static final LocalDate COLLECT_FROM = LocalDate.of(2026, 6, 1);
//    private static final LocalDate COLLECT_TO = LocalDate.of(2026, 6, 2);

    @Autowired
    private PerformanceCollectService performanceCollectService;

    @Test
    void 기간별_공연데이터를_페이지별로_조회해서_DB에_저장한다() {
        int collectedCount = performanceCollectService.collectPerformances(
                COLLECT_FROM,
                COLLECT_TO,
                API_CALL_DELAY_MILLIS
        );

        assertThat(collectedCount).isGreaterThanOrEqualTo(0);
    }
}
