package org.search.feature.blog.search.dataprovider;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.search.configuration.mapstuct.MapstructMapperConfig;
import org.search.feature.blog.search.usecase.BlogSearchRequest;
import org.search.feature.blog.search.usecase.IBlogSearchDataProvider;
import org.search.feature.blog.search.usecase.domain.BlogDocuments;
import org.search.repository.network.kakao.KakaoRestAPIRepository;
import org.search.repository.network.kakao.domain.KakaoSearchBlogResponse;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BlogSearchDataProvider implements IBlogSearchDataProvider {

    private final KakaoRestAPIRepository kakaoRestAPIRepository;

//    @CircuitBreaker(name = "searchBlog", fallbackMethod = "searchBlogFallback")
    @Override
    public BlogDocuments search(String query, String sort, int page, int size) {
        final KakaoSearchBlogResponse response = kakaoRestAPIRepository.searchBlog(query, sort, page, size);
        return KakaoResponseMapper.MAPPER.toBlogDocuments(response);
    }

    public BlogDocuments searchBlogFallback(BlogSearchRequest request, Throwable t) {
        return null;
    }

    @Mapper(config = MapstructMapperConfig.class,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    public interface KakaoDocumentMapper {

        @Mappings({
                @Mapping(source = "url", target = "link"),
                @Mapping(source = "contents", target = "description"),
                @Mapping(source = "dateTime", target = "postDate")
        })
        BlogDocuments.Document toBlogDocument(KakaoSearchBlogResponse.Document document);

    }

    @Mapper(config = MapstructMapperConfig.class,
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            uses = {KakaoDocumentMapper.class},
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
    public interface KakaoResponseMapper {
        KakaoResponseMapper MAPPER = Mappers.getMapper(KakaoResponseMapper.class);

        @Mappings({
                @Mapping(source = "meta.totalCount", target = "total"),
                @Mapping(source = "meta.isEnd", target = "isEnd"),
                @Mapping(source = "documents", target = "documents")
        })
        BlogDocuments toBlogDocuments(KakaoSearchBlogResponse kakaoSearchBlogResponse);
    }

}
