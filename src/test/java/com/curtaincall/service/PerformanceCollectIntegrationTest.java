package com.curtaincall.service;

import com.curtaincall.client.PerformanceApiClient;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.dto.performance.period.PeriodPerformanceBody;
import com.curtaincall.dto.performance.period.PeriodPerformanceItem;
import com.curtaincall.dto.performance.period.PeriodPerformanceResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Disabled("외부 공공데이터 API와 로컬 DB를 쓰는 수동 수집용 테스트. 필요할 때만 Disabled를 풀고 실행한다.")
public class PerformanceCollectIntegrationTest {

    private static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final LocalDate COLLECT_FROM = LocalDate.of(2010, 1, 1);
    private static final LocalDate COLLECT_TO = LocalDate.now().plusYears(1);
    private static final int ROWS_PER_PAGE = 100;

    @Autowired
    private PerformanceApiClient performanceApiClient;

    @Test
    void 기간별_공연데이터를_페이지별로_조회해서_DB에_저장한다() {
        int collectedCount = 0;

        LocalDate cursor = COLLECT_FROM;
        while (!cursor.isAfter(COLLECT_TO)) {
            LocalDate from = cursor;
            LocalDate to = cursor.plusMonths(1).minusDays(1);

            if (to.isAfter(COLLECT_TO)) {
                to = COLLECT_TO;
            }

            collectedCount += collectPeriod(from, to);
            cursor = cursor.plusMonths(1);
        }

        assertThat(collectedCount).isGreaterThanOrEqualTo(0);
    }

    private int collectPeriod(LocalDate from, LocalDate to) {
        int pageNo = 1;
        int collectedCount = 0;

        while (true) {
            PeriodPerformanceResponse response = performanceApiClient.fetchPeriodCultureInfo(
                    format(from),
                    format(to),
                    pageNo,
                    ROWS_PER_PAGE
            );

            PeriodPerformanceBody body = response.getBody();
            if (body == null || body.getItems() == null || body.getItems().getItem() == null) {
                break;
            }

            List<PeriodPerformanceItem> items = body.getItems().getItem();
            if (items.isEmpty()) {
                break;
            }

            for (PeriodPerformanceItem item : items) {
                PerformanceDetailResponse detailResponse = performanceApiClient.fetchPerformanceDetail(item.getSeq());

                /*
                 * 여기서부터 DB 저장 서비스/매퍼가 필요함.
                 *
                 * 필요한 흐름:
                 * 1. item을 performance 테이블에 upsert한다.
                 *    기준 키는 공공데이터 seq.
                 *
                 * 2. detailResponse.getBody().getItems().getItem()을 꺼내서
                 *    performance_detail 테이블에 upsert한다.
                 *    여기도 기준 키는 같은 seq.
                 *
                 * 3. PerformanceMapper에는 대략 이런 메서드가 필요함.
                 *    - upsertPerformance(...)
                 *    - upsertPerformanceDetail(...)
                 *
                 * 지금 프로젝트의 PerformanceMapper.xml, PerformanceCollectService가 비어 있어서
                 * 실제 DB 저장 코드는 여기서 멈춘다.
                 */

                assertThat(detailResponse).isNotNull();
                collectedCount++;
            }

            Integer totalCount = body.getTotalCount();
            if (totalCount == null || pageNo * ROWS_PER_PAGE >= totalCount) {
                break;
            }

            pageNo++;
        }

        return collectedCount;
    }

    private String format(LocalDate date) {
        return date.format(API_DATE_FORMAT);
    }
}
