package org.sample.test.feature.search.keyword.usecase;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTopNSearchKeywordCountsRequest {

  private int size;

}
