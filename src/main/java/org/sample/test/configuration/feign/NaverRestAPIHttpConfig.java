package org.sample.test.configuration.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverRestAPIHttpConfig {

    private static final String HEADER_X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String HEADER_X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Value("${external.rest-api.naver.client-id}")
    private String clientId;

    @Value("${external.rest-api.naver.client-secret}")
    private String clientSecret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
          requestTemplate.header(HEADER_X_NAVER_CLIENT_ID, clientId);
          requestTemplate.header(HEADER_X_NAVER_CLIENT_SECRET, clientSecret);
        };
    }

}
