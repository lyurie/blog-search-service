package org.sample.test.feature.keyword.usecase.getcount;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.sample.test.assets.enums.ErrorCode;
import org.sample.test.assets.exception.BusinessException;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.keyword.domain.SearchKeywordCountDomain;
import org.sample.test.feature.keyword.usecase.ISearchKeywordDataProvider;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class GetSearchKeywordCountUseCase implements GetSearchKeywordCountInputBoundary {

  private final ISearchKeywordDataProvider searchKeywordDataProvider;

  @Override
  public GetSearchKeywordCountResponse execute(GetSearchKeywordCountRequest request) {
    final String searchKeyword = request.getSearchKeyword();
    if (!StringUtils.hasLength(searchKeyword)) {
      throw new BusinessException(ErrorCode.INVALID_REQUEST, "searchKeyword is required");
    }

    final SearchKeywordCountDomain domain = searchKeywordDataProvider.getSearchKeywordCountByKeyword(searchKeyword);

    return ResponseMapper.MAPPER.toResponse(domain);
  }

  @Mapper(config = MapstructMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  public interface ResponseMapper {
    ResponseMapper MAPPER = Mappers.getMapper(ResponseMapper.class);

    GetSearchKeywordCountResponse toResponse(SearchKeywordCountDomain domain);
  }

}
