package org.sample.test.feature.search.keyword.dataprovider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sample.test.feature.search.keyword.domain.SearchKeywordCountDomain;
import org.sample.test.repository.h2.entity.SearchKeywordCountEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SearchKeywordCountDataProviderMapstructTest {

    private SearchKeywordCountDataProvider$EntityMapperImpl entityMapper;

    @BeforeAll
    void beforeAll() {
        entityMapper = new SearchKeywordCountDataProvider$EntityMapperImpl();
    }

    @Test
    void searchKeywordCountDataProvider_entity_mapper_toDomain_test() {
        // given
        SearchKeywordCountEntity entity = new SearchKeywordCountEntity();
        entity.setSearchKeyword("searchKeyword");
        entity.setSearchCount(1);

        // when
        SearchKeywordCountDomain domain = entityMapper.toDomain(entity);

        // then
        assertThat(domain.getSearchKeyword().equals(entity.getSearchKeyword()));
        assertThat(domain.getSearchCount() == entity.getSearchCount());
    }

    @Test
    void searchKeywordCountDataProvider_entity_mapper_toDomainList_test() {
        // given
        SearchKeywordCountEntity entity = new SearchKeywordCountEntity();
        entity.setSearchKeyword("keyword");
        entity.setSearchCount(1);

        List<SearchKeywordCountEntity> entityList = Arrays.asList(entity);

        // when
        List<SearchKeywordCountDomain> domainList = entityMapper.toDomainList(entityList);

        // then
        assertThat(domainList)
            .extracting("searchKeyword", "searchCount")
            .containsExactly(
                tuple(
                    entity.getSearchKeyword(),
                    entity.getSearchCount()
                )
            );
    }
}
