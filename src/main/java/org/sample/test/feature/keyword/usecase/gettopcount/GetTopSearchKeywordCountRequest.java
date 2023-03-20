package org.sample.test.feature.keyword.usecase.gettopcount;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTopSearchKeywordCountRequest {

  private Integer size;

}
