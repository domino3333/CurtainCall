package com.curtaincall.scheduler;


import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.curtaincall.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * api를 주기적으로 호출하여
 * db에 저장하는 컨트롤러
 * */
@RequiredArgsConstructor
@Slf4j
@Component
public class PerformanceCollectScheduler {

    //todo
    // 그러면 performanceController는 어떻게 쓰여야맞는거지
    // 지금은 외부 api호출을 performanceController에서 하고 있는데
    // 그 호출을 그냥 여기서 하고, db저장도 여기서 하는 게 나을 듯
    // performanceController에서는 사용자에게 정보 내려줄 때 사용

    private final PerformanceService performanceService;

    /**
     * 지역별 공연 정보 조회
     * */
    @GetMapping("/area")
    public AreaPerformanceResponse getRegionalCultureInfo(){

        return performanceService.getRegionalCultureInfo();

    }

    /**
     * 공연 정보 상세 조회
     * */
    @GetMapping("/detail/{seq}")
    public PerformanceDetailResponse getPerformanceDetail(@PathVariable Long seq){

        return performanceService.getPerformanceDetail(seq);
    }

}
