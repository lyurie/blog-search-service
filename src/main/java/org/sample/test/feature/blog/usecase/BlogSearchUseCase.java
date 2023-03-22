package org.sample.test.feature.blog.usecase;

import lombok.RequiredArgsConstructor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.sample.test.assets.enums.ErrorCode;
import org.sample.test.assets.exception.BusinessException;
import org.sample.test.configuration.mapstuct.MapstructMapperConfig;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class BlogSearchUseCase implements BlogSearchInputBoundary {

    public static final String ERROR_MESSAGE_WHEN_QUERY_NOT_EXISTS = "request parameter query is required";

    public static final String SEARCH_TARGET_KAKAO = "kakao";
    public static final String SEARCH_TARGET_NAVER = "naver";

    private final IBlogSearchDataProvider blogSearchDataProvider;

    private final DomainMapper domainMapper;

    @Override
    public BlogSearchResponse execute(BlogSearchRequest request) {
        // 1. validate request
        final String query = request.getQuery();
        if(!StringUtils.hasLength(query)) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, ERROR_MESSAGE_WHEN_QUERY_NOT_EXISTS);
        }

        // 2. increase search keyword count
        blogSearchDataProvider.incrSearchKeywordCount(query);

        // 3. search blog results
        final BlogDocumentsDomain blogDocumentsDomain;
        switch (request.getTarget()) {
            case SEARCH_TARGET_KAKAO -> blogDocumentsDomain = blogSearchDataProvider.searchFromKakaoWithNoFallback(
                query,
                request.getSort(),
                request.getPage(),
                request.getSize()
            );
            case SEARCH_TARGET_NAVER -> blogDocumentsDomain = blogSearchDataProvider.searchFromNaverWithNoFallback(
                query,
                request.getSort(),
                request.getPage(),
                request.getSize()
            );
            default -> blogDocumentsDomain = blogSearchDataProvider.searchFromKakaoWithFallback(
                query,
                request.getSort(),
                request.getPage(),
                request.getSize()
            );
        }

        // 4. make results
        return domainMapper.toResponse(blogDocumentsDomain);
    }

    @Mapper(config = MapstructMapperConfig.class,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            uses = {DocumentMapper.class},
            injectionStrategy = InjectionStrategy.CONSTRUCTOR)
    public interface DomainMapper {

        BlogSearchResponse toResponse(BlogDocumentsDomain domain);

    }

    @Mapper(config = MapstructMapperConfig.class, unmappedSourcePolicy = ReportingPolicy.IGNORE)
    public interface DocumentMapper {

        BlogSearchResponse.Document toDocument(BlogDocumentsDomain.Document document);

    }

}
