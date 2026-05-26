package com.curtaincall.client;


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


    //지역별 문화정보 조회 api
    public String fetchRegionalCultureInfo(){
        String encodedKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);

        String url = "https://apis.data.go.kr/B553457/cultureinfo/area2"
                + "?serviceKey=" + encodedKey
                + "&PageNo=1"
                + "&numOfrows=10";

        byte[] responseBytes = restClient.get()
                .uri(URI.create(url))
                .retrieve()
                .body(byte[].class);

        return new String(responseBytes,StandardCharsets.UTF_8);

    }
}
