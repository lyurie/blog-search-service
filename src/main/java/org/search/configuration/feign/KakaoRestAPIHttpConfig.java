package org.search.configuration.feign;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

public class KakaoRestAPIHttpConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
          requestTemplate.header("Authorization", "KakaoAK 0754d4ffa3d9a240b379fe1a1307a679" );
        };
    }

}
