package com.curtaincall.service.Impl;

import com.curtaincall.client.PerformanceApiClient;
import com.curtaincall.domain.Performance;
import com.curtaincall.domain.PerformanceDetailView;
import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailViewResponse;
import com.curtaincall.dto.performance.list.PerformanceListItemResponse;
import com.curtaincall.dto.performance.list.PerformanceListResponse;
import com.curtaincall.mapper.PerformanceMapper;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {

    private final PerformanceApiClient performanceApiClient;
    private final PerformanceMapper performanceMapper;

    @Override
    public PerformanceListResponse getPerformances(int page, int size, String keyword, String realm) {
        int normalizedPage = Math.max(page, 1);
        int normalizedSize = Math.min(Math.max(size, 1), 200);
        int offset = (normalizedPage - 1) * normalizedSize;
        String normalizedKeyword = normalize(keyword);
        String normalizedRealm = normalize(realm);

        int totalCount = performanceMapper.countPerformances(normalizedKeyword, normalizedRealm);
        List<Performance> performances = performanceMapper.selectPerformances(
                normalizedKeyword,
                normalizedRealm,
                offset,
                normalizedSize
        );

        List<PerformanceListItemResponse> items = performances.stream()
                .map(PerformanceListItemResponse::from)
                .toList();

        int totalPages = totalCount == 0 ? 0 : (int) Math.ceil((double) totalCount / normalizedSize);

        return PerformanceListResponse.builder()
                .performances(items)
                .realms(performanceMapper.selectRealms())
                .page(normalizedPage)
                .size(normalizedSize)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .build();
    }

    @Override
    public PerformanceDetailViewResponse getPerformance(Long seq) {
        PerformanceDetailView detailView = performanceMapper.selectPerformanceDetail(seq);

        if (detailView == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "공연정보를 찾을 수 없습니다.");
        }

        return PerformanceDetailViewResponse.from(detailView);
    }

    @Override
    public AreaPerformanceResponse getRegionalCultureInfo() {
        return performanceApiClient.fetchRegionalCultureInfo();
    }

    @Override
    public PerformanceDetailResponse getPerformanceDetail(Long seq) {

        return performanceApiClient.fetchPerformanceDetail(seq);
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }
}
