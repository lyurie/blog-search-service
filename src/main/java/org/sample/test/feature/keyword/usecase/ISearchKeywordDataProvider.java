package org.sample.test.feature.keyword.usecase;

import org.sample.test.feature.keyword.domain.SearchKeywordCountDomain;

import java.util.List;

public interface ISearchKeywordDataProvider {

  SearchKeywordCountDomain getSearchKeywordCountByKeyword(String searchKeyword);

  List<SearchKeywordCountDomain> getTopSearchKeywordCount(int size);

  SearchKeywordCountDomain incrSearchKeywordCount(String searchKeyword);

}
