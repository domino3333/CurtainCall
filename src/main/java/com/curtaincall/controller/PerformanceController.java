package com.curtaincall.controller;


import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/api/performance/list")
    public AreaPerformanceResponse getPerform(){
        //todo 메서드 이름 변경하고
        // 데이터 구조 파악하기

    }
}
