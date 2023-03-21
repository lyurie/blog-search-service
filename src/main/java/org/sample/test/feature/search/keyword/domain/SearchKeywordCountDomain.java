package org.sample.test.feature.search.keyword.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SearchKeywordCountDomain {

    private String searchKeyword;

    private int searchCount;

}
