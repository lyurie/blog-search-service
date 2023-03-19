package org.sample.test.repository.network.kakao;

import org.sample.test.configuration.feign.KakaoRestAPIHttpConfig;
import org.sample.test.repository.network.kakao.domain.KakaoSearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoRestAPIClient", url = "https://dapi.kakao.com", configuration = KakaoRestAPIHttpConfig.class )
public interface KakaoRestAPIRepository {

    @GetMapping("/v2/search/blog")
    KakaoSearchBlogResponse searchBlog(@RequestParam String query,
                                       @RequestParam String sort,
                                       @RequestParam int page,
                                       @RequestParam int size);

}
