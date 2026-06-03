package com.curtaincall.dto.performance.detail;

import com.curtaincall.domain.PerformanceDetailView;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PerformanceDetailViewResponse {

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

    public static PerformanceDetailViewResponse from(PerformanceDetailView view) {
        return PerformanceDetailViewResponse.builder()
                .seq(view.getSeq())
                .serviceName(unescape(view.getServiceName()))
                .title(unescape(view.getTitle()))
                .startDate(view.getStartDate())
                .endDate(view.getEndDate())
                .place(unescape(view.getPlace()))
                .realmName(unescape(view.getRealmName()))
                .area(unescape(view.getArea()))
                .sigungu(unescape(view.getSigungu()))
                .thumbnail(view.getThumbnail())
                .gpsX(view.getGpsX())
                .gpsY(view.getGpsY())
                .price(unescape(view.getPrice()))
                .contents(unescape(view.getContents()))
                .url(view.getUrl())
                .phone(unescape(view.getPhone()))
                .imgUrl(view.getImgUrl())
                .placeUrl(view.getPlaceUrl())
                .placeAddr(unescape(view.getPlaceAddr()))
                .placeSeq(view.getPlaceSeq())
                .build();
    }

    private static String unescape(String value) {
        if (value == null) {
            return null;
        }

        return HtmlUtils.htmlUnescape(value);
    }
}
