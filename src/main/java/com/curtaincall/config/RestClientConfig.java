package com.curtaincall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    // 내 백엔드 서버가 다른 서버의 api를 호출할 때 필요한 http 설정도구
    //RestTemplate
    //RestClient
    //WebClient

    @Bean
    public RestClient restClient(){
        return RestClient.create();
    }
}
