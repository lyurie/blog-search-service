package org.sample.test.feature.blog.controller;

import lombok.RequiredArgsConstructor;
import org.sample.test.feature.blog.usecase.BlogSearchInputBoundary;
import org.sample.test.feature.blog.usecase.BlogSearchRequest;
import org.sample.test.feature.blog.usecase.BlogSearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BlogSearchController {

    private final BlogSearchInputBoundary blogSearchInputBoundary;

    @GetMapping("/search-blog")
    public ResponseEntity<BlogSearchResponse> searchBlog(@RequestParam Optional<String> query,
                                     @RequestParam Optional<String> sort,
                                     @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> target) {
        BlogSearchRequest request = BlogSearchRequest.builder()
                .query(query.orElse(""))
                .sort(sort.orElse("accuracy"))
                .page(page.orElse(1))
                .size(size.orElse(10))
                .target(target.orElse(""))
                .build();

        return new ResponseEntity<>(blogSearchInputBoundary.search(request), HttpStatus.OK);
    }

}
