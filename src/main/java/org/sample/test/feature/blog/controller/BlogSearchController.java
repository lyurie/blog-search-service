package org.sample.test.feature.blog.controller;

import lombok.RequiredArgsConstructor;
import org.sample.test.feature.blog.usecase.BlogSearchInputBoundary;
import org.sample.test.feature.blog.usecase.BlogSearchRequest;
import org.sample.test.feature.blog.usecase.BlogSearchResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlogSearchController {

    private final BlogSearchInputBoundary blogSearchInputBoundary;

    @GetMapping("/search-blog")
    public BlogSearchResponse searchBlog(@RequestParam(defaultValue = "") String query,
                                         @RequestParam(defaultValue = "accuracy") String sort,
                                         @RequestParam(defaultValue = "1") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "") String target) {

        BlogSearchRequest request = BlogSearchRequest.builder()
            .query(query)
            .sort(sort)
            .page(page)
            .size(size)
            .target(target)
            .build();

        return blogSearchInputBoundary.execute(request);
    }

}
