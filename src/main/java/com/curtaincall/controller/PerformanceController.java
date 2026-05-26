package com.curtaincall.controller;


import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/performance/")
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/area")
    public AreaPerformanceResponse getRegionalCultureInfo(){
        
        //todo 데이터 구조 파악하기
        return performanceService.getRegionalCultureInfo();


    }
}
