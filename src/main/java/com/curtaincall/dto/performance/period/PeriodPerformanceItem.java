package com.curtaincall.dto.performance.period;

import lombok.Data;

@Data
public class PeriodPerformanceItem {

    private String serviceName;
    private Long seq;
    private String title;
    private String startDate;
    private String endDate;
    private String place;
    private String realmName;
    private String area;
    private String sigungu;
    private String thumbnail;
    private Double gpsX;
    private Double gpsY;
}
