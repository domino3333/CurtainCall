package com.curtaincall.client;


import com.curtaincall.dto.performance.area.AreaPerformanceResponse;
import com.curtaincall.dto.performance.detail.PerformanceDetailResponse;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class PerformanceApiClient {

    private final RestClient restClient;

    @Value("${performance.api.base-url}")
    private String baseUrl;

    @Value("${performance.api.service-key}")
    private String serviceKey;


    /**
     * 지역별 문화정보 조회 api
     * */
    public AreaPerformanceResponse fetchRegionalCultureInfo(){
        String encodedKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);

        String url = baseUrl + "/area2"
                + "?serviceKey=" + encodedKey
                + "&PageNo=1"
                + "&numOfrows=10";

        byte[] responseBytes = restClient.get()
                .uri(URI.create(url))
                .retrieve()
                .body(byte[].class);

        String xml = new String(responseBytes,StandardCharsets.UTF_8);

        try{
            XmlMapper xmlMapper =new XmlMapper();
            return xmlMapper.readValue(xml,AreaPerformanceResponse.class);
        }catch(Exception e){
            throw new RuntimeException("지역별 공연정보 xml 파싱 실패",e);
        }

    }

    /**
     * 문화 정보 상세 조회 api
     * */
    public PerformanceDetailResponse fetchPerformanceDetail(String seq) {
        String encodedKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);

        String url = baseUrl + "/detail2"
                + "?serviceKey=" + encodedKey
                + "&seq=" + seq;

        byte[] responseBytes = restClient.get()
                .uri(URI.create(url))
                .retrieve()
                .body(byte[].class);

        String xml = new String(responseBytes, StandardCharsets.UTF_8);

        try {
            XmlMapper xmlMapper = new XmlMapper();
            return xmlMapper.readValue(xml, PerformanceDetailResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("공연 상세정보 xml 파싱 실패", e);
        }
    }








}
