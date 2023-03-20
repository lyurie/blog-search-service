package org.sample.test.feature.keyword.dataprovider;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.keyword.domain.SearchKeywordCountDomain;
import org.sample.test.feature.keyword.usecase.ISearchKeywordDataProvider;
import org.sample.test.repository.h2.BlogSearchKeywordCountRepository;
import org.sample.test.repository.h2.entity.BlogSearchKeywordCountEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SearchKeywordDataProvider implements ISearchKeywordDataProvider {

  private final BlogSearchKeywordCountRepository blogSearchKeywordCountRepository;

  @Override
  public SearchKeywordCountDomain getSearchKeywordCountByKeyword(String searchKeyword) {
    final BlogSearchKeywordCountEntity entity = blogSearchKeywordCountRepository.findBySearchKeyword(searchKeyword);
    if (entity != null) {
      return DomainMapper.MAPPER.toDomain(entity);
    }
    return null;
  }

  @Override
  public List<SearchKeywordCountDomain> getTopSearchKeywordCount(int size) {
    final List<BlogSearchKeywordCountEntity> entities = blogSearchKeywordCountRepository.findAll(PageRequest.of(0, size, Sort.by("searchCount").descending()))
      .stream()
      .collect(Collectors.toList());

    return DomainMapper.MAPPER.toDomainList(entities);
  }

  @Transactional
  @Override
  public SearchKeywordCountDomain incrSearchKeywordCount(String searchKeyword) {
    BlogSearchKeywordCountEntity entity = blogSearchKeywordCountRepository.findBySearchKeyword(searchKeyword);

    if (entity != null) {
      entity.setSearchCount(entity.getSearchCount() + 1);
    } else {
      entity = new BlogSearchKeywordCountEntity();
      entity.setSearchKeyword(searchKeyword);
      entity.setSearchCount(1);
    }

    final BlogSearchKeywordCountEntity savedResult = blogSearchKeywordCountRepository.save(entity);

    return DomainMapper.MAPPER.toDomain(savedResult);
  }

  @Mapper(config = MapstructMapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
  public interface DomainMapper {
    DomainMapper MAPPER = Mappers.getMapper(DomainMapper.class);

    SearchKeywordCountDomain toDomain(BlogSearchKeywordCountEntity entity);
    List<SearchKeywordCountDomain> toDomainList(List<BlogSearchKeywordCountEntity> entities);
  }

}
