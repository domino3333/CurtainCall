package com.curtaincall.controller;


import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

    private final PerformanceService performanceService;

    @GetMapping("/api/performance/test")
    public String getPerform(){
        return performanceService.getPerformanceApiTest();
    }
}
