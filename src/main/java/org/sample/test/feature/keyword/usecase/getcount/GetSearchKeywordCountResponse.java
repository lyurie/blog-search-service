package org.sample.test.feature.keyword.usecase.getcount;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetSearchKeywordCountResponse {

  private String searchKeyword;

  private Integer searchCount;

}
