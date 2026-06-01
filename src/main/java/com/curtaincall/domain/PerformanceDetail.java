package com.curtaincall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceDetail {

    private Long seq;
    private String price;
    private String contents;
    private String url;
    private String phone;
    private String imgUrl;
    private String placeUrl;
    private String placeAddr;
    private Long placeSeq;
}
