package org.sample.test.configuration.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

public class KakaoRestAPIHttpConfig {

    @Value("${external.rest-api.kakao.authorization}")
    private String authorizationHeader;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
          requestTemplate.header(HttpHeaders.AUTHORIZATION, authorizationHeader);
        };
    }

}
