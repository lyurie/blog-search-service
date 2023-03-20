package org.sample.test.feature.keyword.controller;

import lombok.RequiredArgsConstructor;
import org.sample.test.feature.keyword.dataprovider.SearchKeywordDataProvider;
import org.sample.test.feature.keyword.domain.SearchKeywordCountDomain;
import org.sample.test.feature.keyword.usecase.getcount.GetSearchKeywordCountInputBoundary;
import org.sample.test.feature.keyword.usecase.getcount.GetSearchKeywordCountRequest;
import org.sample.test.feature.keyword.usecase.getcount.GetSearchKeywordCountResponse;
import org.sample.test.feature.keyword.usecase.gettopcount.GetTopSearchKeywordCountInputBoundary;
import org.sample.test.feature.keyword.usecase.gettopcount.GetTopSearchKeywordCountRequest;
import org.sample.test.feature.keyword.usecase.gettopcount.GetTopSearchKeywordCountResponse;
import org.sample.test.feature.keyword.usecase.incrcount.IncrSearchKeywordCountRequest;
import org.sample.test.repository.h2.BlogSearchKeywordCountRepository;
import org.sample.test.repository.h2.entity.BlogSearchKeywordCountEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class SearchKeywordController {

    private final GetSearchKeywordCountInputBoundary getSearchKeywordCountInputBoundary;
    private final GetTopSearchKeywordCountInputBoundary getTopSearchKeywordCountInputBoundary;

    private final SearchKeywordDataProvider searchKeywordDataProvider;

    @GetMapping("/search-keyword-count")
    public ResponseEntity<GetSearchKeywordCountResponse> searchKeywordCount(@RequestParam String keyword) {
        GetSearchKeywordCountRequest request = GetSearchKeywordCountRequest.builder()
          .searchKeyword(keyword)
          .build();

        final GetSearchKeywordCountResponse response = getSearchKeywordCountInputBoundary.execute(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/top-search-keyword-counts")
    public ResponseEntity<List<GetTopSearchKeywordCountResponse>> topSearchKeywordCounts(@RequestParam Optional<Integer> size) {
        GetTopSearchKeywordCountRequest request = GetTopSearchKeywordCountRequest.builder()
          .size(size.orElse(10))
          .build();

        final List<GetTopSearchKeywordCountResponse> response = getTopSearchKeywordCountInputBoundary.execute(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/incr-search-keyword-count")
    public ResponseEntity<SearchKeywordCountDomain> incrSearchKeywordCount(@RequestBody IncrSearchKeywordCountRequest request) {
        final SearchKeywordCountDomain domain = searchKeywordDataProvider.incrSearchKeywordCount(request.getSearchKeyword());

        return new ResponseEntity<>(domain, HttpStatus.OK);
    }

}
