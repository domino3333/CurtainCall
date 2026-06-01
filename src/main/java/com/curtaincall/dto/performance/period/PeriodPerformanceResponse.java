package com.curtaincall.dto.performance.period;


import com.curtaincall.dto.performance.header.PerformanceApiHeader;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "response")
public class PeriodPerformanceResponse {

    PerformanceApiHeader header;
    PeriodPerformanceBody body;

}
