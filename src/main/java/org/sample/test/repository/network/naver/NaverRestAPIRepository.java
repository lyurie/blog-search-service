package org.sample.test.repository.network.naver;

import org.sample.test.configuration.feign.NaverRestAPIHttpConfig;
import org.sample.test.repository.network.naver.domain.NaverSearchBlogResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverRestAPIClient", url = "${external.rest-api.naver.url}", configuration = NaverRestAPIHttpConfig.class)
public interface NaverRestAPIRepository {

    @GetMapping("/v1/search/blog.json")
    NaverSearchBlogResponse searchBlog(@RequestParam String query,
                                       @RequestParam int display,
                                       @RequestParam int start,
                                       @RequestParam String sort);
}
