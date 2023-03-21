package org.sample.test.feature.search.keyword.usecase;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.sample.test.assets.enums.ErrorCode;
import org.sample.test.assets.exception.BusinessException;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.search.keyword.domain.SearchKeywordCountDomain;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetTopNSearchKeywordCountsUseCase implements GetTopNSearchKeywordCountsInputBoundary {

  private final ISearchKeywordCountDataProvider searchKeywordCountDataProvider;

  private final ResponseMapper responseMapper;

  @Override
  public List<GetTopNSearchKeywordCountsResponse> execute(GetTopNSearchKeywordCountsRequest request) {
    // 1. validate request
    final int size = request.getSize();
    if (size < 1 || size > 10) {
      throw new BusinessException(ErrorCode.INVALID_REQUEST, "size should be min 1, max 10");
    }

    // 2. get top n search keyword count
    final List<SearchKeywordCountDomain> domains = searchKeywordCountDataProvider.getTopNSearchKeywordCounts(size);

    // 3. make results
    return responseMapper.toResponses(domains);
  }

  @Mapper(config = MapstructMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  public interface ResponseMapper {

    List<GetTopNSearchKeywordCountsResponse> toResponses(List<SearchKeywordCountDomain> domains);

  }

}
