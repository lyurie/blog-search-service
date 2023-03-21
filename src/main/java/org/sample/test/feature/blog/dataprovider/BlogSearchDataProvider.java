package org.sample.test.feature.blog.dataprovider;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.*;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;
import org.sample.test.feature.blog.usecase.IBlogSearchDataProvider;
import org.sample.test.repository.h2.SearchKeywordCountRepository;
import org.sample.test.repository.h2.entity.SearchKeywordCountEntity;
import org.sample.test.repository.network.kakao.KakaoRestAPIRepository;
import org.sample.test.repository.network.kakao.domain.KakaoSearchBlogResponse;
import org.sample.test.repository.network.naver.NaverRestAPIRepository;
import org.sample.test.repository.network.naver.domain.NaverSearchBlogResponse;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BlogSearchDataProvider implements IBlogSearchDataProvider {

    private final SearchKeywordCountRepository searchKeywordCountRepository;

    private final KakaoRestAPIRepository kakaoRestAPIRepository;
    private final NaverRestAPIRepository naverRestAPIRepository;

    private final KakaoResponseMapper kakaoResponseMapper;
    private final NaverResponseMapper naverResponseMapper;

    @Transactional
    @Override
    public void incrSearchKeywordCount(String searchKeyword) {
        SearchKeywordCountEntity entity = searchKeywordCountRepository.findBySearchKeyword(searchKeyword);

        if (entity != null) {
            entity.setSearchCount(entity.getSearchCount() + 1);
        } else {
            entity = new SearchKeywordCountEntity();
            entity.setSearchKeyword(searchKeyword);
            entity.setSearchCount(1);
        }

        searchKeywordCountRepository.save(entity);
    }

    @Override
    public BlogDocumentsDomain searchFromKakaoWithNoFallback(String query, String sort, int page, int size) {
        final KakaoSearchBlogResponse response = kakaoRestAPIRepository.searchBlog(query, sort, page, size);
        return kakaoResponseMapper.toDomain(response, page, size);
    }

    @CircuitBreaker(name = "searchFromKakao", fallbackMethod = "searchFromKakaoFallback")
    @Override
    public BlogDocumentsDomain searchFromKakaoWithFallback(String query, String sort, int page, int size) {
        return searchFromKakaoWithNoFallback(query, sort, page, size);
    }

    @Override
    public BlogDocumentsDomain searchFromNaverWithNoFallback(String query, String sort, int page, int size) {
        final NaverSearchBlogResponse response = naverRestAPIRepository.searchBlog(query, size, (page - 1) * size + 1, sort.equals("accuracy") ? "sim" : "date");
        return naverResponseMapper.toDomain(response, page, size);
    }

    public BlogDocumentsDomain searchFromKakaoFallback(String query, String sort, int page, int size, Throwable t) {
//        log.warn("searchFromKakaoFallback method executed for query : {}", query);
        return searchFromNaverWithNoFallback(query, sort, page, size);
    }

    @Mapper(config = MapstructMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {KakaoDocumentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
    public interface KakaoResponseMapper {

        @Mappings({
            @Mapping(target = "total", source = "response.meta.totalCount"),
            @Mapping(target = "documents", source = "response.documents")
        })
        BlogDocumentsDomain toDomain(KakaoSearchBlogResponse response, int page, int size);

    }

    @Mapper(config = MapstructMapperConfig.class,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface KakaoDocumentMapper {

        @Mappings({
                @Mapping(target = "link", source = "url"),
                @Mapping(target = "description", source = "contents"),
                @Mapping(target = "postDate", source = "dateTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
        })
        BlogDocumentsDomain.Document toDocument(KakaoSearchBlogResponse.Document document);

    }

    @Mapper(config = MapstructMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {NaverDocumentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
    public interface NaverResponseMapper {

        @Mappings({
            @Mapping(target = "total", source = "response.total"),
            @Mapping(target = "documents", source = "response.items")
        })
        BlogDocumentsDomain toDomain(NaverSearchBlogResponse response, int page, int size);

    }

    @Mapper(config = MapstructMapperConfig.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface NaverDocumentMapper {

        @Mappings({
            @Mapping(target = "blogName", source = "bloggerName"),
            @Mapping(target = "postDate", source = "postDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
        })
        BlogDocumentsDomain.Document toDocument(NaverSearchBlogResponse.Item item);

    }

}
