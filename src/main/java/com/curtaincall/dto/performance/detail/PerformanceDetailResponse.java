package com.curtaincall.dto.performance.detail;


import com.curtaincall.dto.performance.area.AreaPerformanceBody;
import com.curtaincall.dto.performance.header.PerformanceApiHeader;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "response")
public class PerformanceDetailResponse {

    private PerformanceApiHeader header;
    private PerformanceDetailBody body;
}
