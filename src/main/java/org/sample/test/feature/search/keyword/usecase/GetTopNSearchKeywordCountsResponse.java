package org.sample.test.feature.search.keyword.usecase;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTopNSearchKeywordCountsResponse {

  @JsonProperty("search_keyword")
  private String searchKeyword;

  @JsonProperty("search_count")
  private Integer searchCount;

}
