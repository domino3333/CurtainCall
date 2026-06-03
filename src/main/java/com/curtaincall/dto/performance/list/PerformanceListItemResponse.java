package com.curtaincall.dto.performance.list;

import com.curtaincall.domain.Performance;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.HtmlUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PerformanceListItemResponse {

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

    public static PerformanceListItemResponse from(Performance performance) {
        return PerformanceListItemResponse.builder()
                .seq(performance.getSeq())
                .serviceName(unescape(performance.getServiceName()))
                .title(unescape(performance.getTitle()))
                .startDate(performance.getStartDate())
                .endDate(performance.getEndDate())
                .place(unescape(performance.getPlace()))
                .realmName(unescape(performance.getRealmName()))
                .area(unescape(performance.getArea()))
                .sigungu(unescape(performance.getSigungu()))
                .thumbnail(performance.getThumbnail())
                .gpsX(performance.getGpsX())
                .gpsY(performance.getGpsY())
                .build();
    }

    private static String unescape(String value) {
        if (value == null) {
            return null;
        }

        return HtmlUtils.htmlUnescape(value);
    }
}
