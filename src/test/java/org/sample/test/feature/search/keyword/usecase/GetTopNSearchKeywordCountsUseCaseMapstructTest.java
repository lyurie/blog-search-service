package org.sample.test.feature.search.keyword.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sample.test.feature.search.keyword.domain.SearchKeywordCountDomain;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetTopNSearchKeywordCountsUseCaseMapstructTest {

    private GetTopNSearchKeywordCountsUseCase$DomainMapperImpl domainMapper;

    @BeforeAll
    void beforeAll() {
        domainMapper = new GetTopNSearchKeywordCountsUseCase$DomainMapperImpl();
    }

    @Test
    void getTopNSearchKeywordCountsUseCase_domain_mapper_test() {
        // given
        SearchKeywordCountDomain domain = SearchKeywordCountDomain.builder()
            .searchKeyword("searchKeyword")
            .searchCount(1)
            .build();

        // when
        List<GetTopNSearchKeywordCountsResponse> responseList = domainMapper.toResponses(Arrays.asList(domain));

        // test
        assertThat(responseList)
            .extracting("searchKeyword", "searchCount")
            .containsExactly(
                tuple(
                    domain.getSearchKeyword(),
                    domain.getSearchCount()
                )
            );
    }

}
