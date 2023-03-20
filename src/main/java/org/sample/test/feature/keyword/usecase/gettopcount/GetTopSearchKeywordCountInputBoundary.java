package org.sample.test.feature.keyword.usecase.gettopcount;

import java.util.List;

public interface GetTopSearchKeywordCountInputBoundary {

  List<GetTopSearchKeywordCountResponse> execute(final GetTopSearchKeywordCountRequest request);

}
