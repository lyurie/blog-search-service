package org.sample.test.feature.search.keyword.dataprovider;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.search.keyword.domain.SearchKeywordCountDomain;
import org.sample.test.feature.search.keyword.usecase.ISearchKeywordCountDataProvider;
import org.sample.test.repository.h2.SearchKeywordCountRepository;
import org.sample.test.repository.h2.entity.SearchKeywordCountEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SearchKeywordCountDataProvider implements ISearchKeywordCountDataProvider {

    private final SearchKeywordCountRepository searchKeywordCountRepository;

    private final DomainMapper domainMapper;

    private static final int MIN_SEARCH_COUNT = 0; // search count cardinality 를 높이기 위해 매우 낮은 카운팅 숫자는 제외하도록 함
    private static final String SORT_BY_SEARCH_COUNT = "searchCount";

    @Override
    @Cacheable(cacheNames = "topSearchKeywords", key = "{#size}")
    public List<SearchKeywordCountDomain> getTopNSearchKeywordCounts(int size) {
        final List<SearchKeywordCountEntity> entities = searchKeywordCountRepository.findBySearchCountGreaterThan(
            MIN_SEARCH_COUNT, PageRequest.of(0, size, Sort.by(SORT_BY_SEARCH_COUNT).descending()));

        return domainMapper.toDomainList(entities);
    }

    @Mapper(config = MapstructMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface DomainMapper {

        SearchKeywordCountDomain toDomain(SearchKeywordCountEntity entity);

        List<SearchKeywordCountDomain> toDomainList(List<SearchKeywordCountEntity> entities);

    }

}
