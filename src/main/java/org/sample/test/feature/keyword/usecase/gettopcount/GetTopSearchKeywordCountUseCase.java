package org.sample.test.feature.keyword.usecase.gettopcount;

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

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetTopSearchKeywordCountUseCase implements GetTopSearchKeywordCountInputBoundary {

  private final ISearchKeywordDataProvider searchKeywordDataProvider;

  @Override
  public List<GetTopSearchKeywordCountResponse> execute(GetTopSearchKeywordCountRequest request) {
    final int size = request.getSize();
    if (size > 10) {
      throw new BusinessException(ErrorCode.INVALID_REQUEST, "size max value is 10");
    }

    final List<SearchKeywordCountDomain> domains = searchKeywordDataProvider.getTopSearchKeywordCount(size);

    return ResponseMapper.MAPPER.toResponses(domains);
  }

  @Mapper(config = MapstructMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  public interface ResponseMapper {
    ResponseMapper MAPPER = Mappers.getMapper(ResponseMapper.class);

    List<GetTopSearchKeywordCountResponse> toResponses(List<SearchKeywordCountDomain> domains);
  }

}
