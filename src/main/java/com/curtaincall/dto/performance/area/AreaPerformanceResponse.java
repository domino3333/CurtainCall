package com.curtaincall.dto.performance.area;


import com.curtaincall.dto.performance.header.PerformanceApiHeader;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "response")
public class AreaPerformanceResponse {

    private PerformanceApiHeader header;
    private AreaPerformanceBody body;
}
