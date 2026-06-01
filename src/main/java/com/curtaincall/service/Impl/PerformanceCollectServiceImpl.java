package com.curtaincall.service.Impl;

import com.curtaincall.client.PerformanceApiClient;
import com.curtaincall.domain.Performance;
import com.curtaincall.domain.PerformanceDetail;
import com.curtaincall.dto.performance.detail.PerformanceDetailItem;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.dto.performance.period.PeriodPerformanceBody;
import com.curtaincall.dto.performance.period.PeriodPerformanceItem;
import com.curtaincall.dto.performance.period.PeriodPerformanceResponse;
import com.curtaincall.mapper.PerformanceMapper;
import com.curtaincall.service.PerformanceCollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceCollectServiceImpl implements PerformanceCollectService {

    private static final DateTimeFormatter API_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final int ROWS_PER_PAGE = 100;

    private final PerformanceApiClient performanceApiClient;
    private final PerformanceMapper performanceMapper;

    @Override
    public int collectPerformances(LocalDate from, LocalDate to) {
        return collectPerformances(from, to, 0);
    }

    @Override
    public int collectPerformances(LocalDate from, LocalDate to, long delayMillis) {
        int collectedCount = 0;

        LocalDate cursor = from;
        while (!cursor.isAfter(to)) {
            LocalDate periodFrom = cursor;
            LocalDate periodTo = cursor.plusMonths(1).minusDays(1);

            if (periodTo.isAfter(to)) {
                periodTo = to;
            }

            collectedCount += collectPeriod(periodFrom, periodTo, delayMillis);
            cursor = cursor.plusMonths(1);
        }

        return collectedCount;
    }

    private int collectPeriod(LocalDate from, LocalDate to, long delayMillis) {
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
                savePerformance(item);
                sleep(delayMillis);
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

    private void savePerformance(PeriodPerformanceItem item) {
        performanceMapper.upsertPerformance(toPerformance(item));

        PerformanceDetailResponse detailResponse = performanceApiClient.fetchPerformanceDetail(item.getSeq());
        PerformanceDetailItem detailItem = getDetailItem(detailResponse);

        if (detailItem != null) {
            performanceMapper.upsertPerformanceDetail(toPerformanceDetail(detailItem));
        }
    }

    private PerformanceDetailItem getDetailItem(PerformanceDetailResponse response) {
        if (response == null || response.getBody() == null || response.getBody().getItems() == null) {
            return null;
        }

        return response.getBody().getItems().getItem();
    }

    private Performance toPerformance(PeriodPerformanceItem item) {
        return Performance.builder()
                .seq(item.getSeq())
                .serviceName(item.getServiceName())
                .title(item.getTitle())
                .startDate(parseDate(item.getStartDate()))
                .endDate(parseDate(item.getEndDate()))
                .place(item.getPlace())
                .realmName(item.getRealmName())
                .area(item.getArea())
                .sigungu(item.getSigungu())
                .thumbnail(item.getThumbnail())
                .gpsX(toBigDecimal(item.getGpsX()))
                .gpsY(toBigDecimal(item.getGpsY()))
                .build();
    }

    private PerformanceDetail toPerformanceDetail(PerformanceDetailItem item) {
        return PerformanceDetail.builder()
                .seq(item.getSeq())
                .price(item.getPrice())
                .contents(item.getContents1())
                .url(item.getUrl())
                .phone(item.getPhone())
                .imgUrl(item.getImgUrl())
                .placeUrl(item.getPlaceUrl())
                .placeAddr(item.getPlaceAddr())
                .placeSeq(parseLong(item.getPlaceSeq()))
                .build();
    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isBlank()) {
            return null;
        }

        return LocalDate.parse(date, API_DATE_FORMAT);
    }

    private BigDecimal toBigDecimal(Double value) {
        if (value == null) {
            return null;
        }

        return BigDecimal.valueOf(value);
    }

    private Long parseLong(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return Long.parseLong(value);
    }

    private String format(LocalDate date) {
        return date.format(API_DATE_FORMAT);
    }

    private void sleep(long delayMillis) {
        if (delayMillis <= 0) {
            return;
        }

        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("공연정보 수집 대기 중 인터럽트 발생", e);
        }
    }
}
