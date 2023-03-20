package org.sample.test.feature.keyword.usecase.gettopcount;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTopSearchKeywordCountResponse {

  private String searchKeyword;

  private Integer searchCount;

}
