package com.curtaincall.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PerformanceDetailView {

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
    private String price;
    private String contents;
    private String url;
    private String phone;
    private String imgUrl;
    private String placeUrl;
    private String placeAddr;
    private Long placeSeq;
}
