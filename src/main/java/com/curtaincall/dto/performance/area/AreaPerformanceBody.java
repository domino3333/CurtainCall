package com.curtaincall.dto.performance.area;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AreaPerformanceBody {

    private Integer totalCount;

    @JacksonXmlProperty(localName = "PageNo")
    private Integer pageNo;

    private Integer numOfrows;

    private AreaPerformanceItems items;
}
