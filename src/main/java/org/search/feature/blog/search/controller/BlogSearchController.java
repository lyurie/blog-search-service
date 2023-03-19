package org.search.feature.blog.search.controller;

import lombok.RequiredArgsConstructor;
import org.search.feature.blog.search.usecase.BlogSearchInputBoundary;
import org.search.feature.blog.search.usecase.BlogSearchRequest;
import org.search.feature.blog.search.usecase.BlogSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BlogSearchController {

    private final BlogSearchInputBoundary blogSearchInputBoundary;

    @GetMapping("/search/blog")
    public BlogSearchResponse searchBlog(@RequestParam Optional<String> query,
                                         @RequestParam Optional<String> sort,
                                         @RequestParam Optional<Integer> page,
                                         @RequestParam Optional<Integer> size,
                                         @RequestParam Optional<String> target) {
        BlogSearchRequest request = BlogSearchRequest.builder()
                .query(query.orElse("카카오"))
                .sort(sort.orElse("accuracy"))
                .page(page.orElse(1))
                .size(size.orElse(10))
                .target(target.orElse("kakao"))
                .build();

        return blogSearchInputBoundary.search(request);
    }

}
