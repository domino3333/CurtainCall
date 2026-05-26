package com.curtaincall.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class PerformanceApiClient {

    private final RestClient restClient;
}
