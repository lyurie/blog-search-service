package org.sample.test.feature.keyword.controller;

import lombok.RequiredArgsConstructor;
import org.sample.test.repository.h2.BlogSearchKeywordCountRepository;
import org.sample.test.repository.h2.entity.BlogSearchKeywordCount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class KeywordSearchController {

    private final BlogSearchKeywordCountRepository blogSearchKeywordCountRepository;

    @GetMapping("/search-keyword/count")
    public ResponseEntity<List<BlogSearchKeywordCount>> searchKeywordCount(@RequestParam String keyword) {
        final List<BlogSearchKeywordCount> list = blogSearchKeywordCountRepository.findBySearchKeyword(keyword);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
