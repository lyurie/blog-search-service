package org.sample.test.feature.search.keyword.controller;

import lombok.RequiredArgsConstructor;
import org.sample.test.feature.search.keyword.usecase.GetTopNSearchKeywordCountsInputBoundary;
import org.sample.test.feature.search.keyword.usecase.GetTopNSearchKeywordCountsRequest;
import org.sample.test.feature.search.keyword.usecase.GetTopNSearchKeywordCountsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchKeywordController {

    private final GetTopNSearchKeywordCountsInputBoundary getTopNSearchKeywordCountsInputBoundary;

    @GetMapping("/top-n-search-keywords")
    public List<GetTopNSearchKeywordCountsResponse> topSearchKeywordCounts(@RequestParam(defaultValue = "10") int size) {

        GetTopNSearchKeywordCountsRequest request = GetTopNSearchKeywordCountsRequest.builder()
          .size(size)
          .build();

        return getTopNSearchKeywordCountsInputBoundary.execute(request);
    }


}
