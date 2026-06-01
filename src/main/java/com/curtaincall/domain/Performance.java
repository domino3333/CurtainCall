package com.curtaincall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {

    private Long seq;
    private String serviceName;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String place;
    private String realmName;
    private String area;
    private String sigungu;
    private String thumbnail;
    private BigDecimal gpsX;
    private BigDecimal gpsY;
}
