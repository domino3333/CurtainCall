package com.curtaincall.dto.performance.period;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class PeriodPerformanceBody {

    private Integer totalCount;

    @JacksonXmlProperty(localName = "PageNo")
    private Integer pageNo;

    private Integer numOfrows;

    private PeriodPerformanceItems items;

}
