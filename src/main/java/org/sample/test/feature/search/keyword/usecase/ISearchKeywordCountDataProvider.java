package org.sample.test.feature.search.keyword.usecase;

import org.sample.test.feature.search.keyword.domain.SearchKeywordCountDomain;

import java.util.List;

public interface ISearchKeywordCountDataProvider {

  List<SearchKeywordCountDomain> getTopNSearchKeywordCounts(int size);

}
