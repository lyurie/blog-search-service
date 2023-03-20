package org.sample.test.feature.keyword.usecase.getcount;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetSearchKeywordCountRequest {

  private String searchKeyword;

}
