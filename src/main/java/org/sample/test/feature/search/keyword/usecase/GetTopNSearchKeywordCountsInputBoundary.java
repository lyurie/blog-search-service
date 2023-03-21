package org.sample.test.feature.search.keyword.usecase;

import java.util.List;

public interface GetTopNSearchKeywordCountsInputBoundary {

  List<GetTopNSearchKeywordCountsResponse> execute(final GetTopNSearchKeywordCountsRequest request);

}
