package org.sample.test.configuration.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class NaverRestAPIHttpConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
          requestTemplate.header("X-Naver-Client-Id", "ITUaf4f6C5Q_s5sR10PQ" );
          requestTemplate.header("X-Naver-Client-Secret", "gQtx9c66Qg" );
        };
    }

}
