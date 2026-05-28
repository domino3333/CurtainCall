package com.curtaincall.controller;


import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performance/")
public class PerformanceController {

    private final PerformanceService performanceService;

    /**
    * 지역별 공연 정보 조회
    * */
    @GetMapping("/area")
    public AreaPerformanceResponse getRegionalCultureInfo(){

        return performanceService.getRegionalCultureInfo();

    }

    @GetMapping("/detail/{seq}")
    public PerformanceDetailResponse getPerformanceDetail(@PathVariable Long seq){

        return performanceService.getPerformanceDetail(seq);
    }
}
