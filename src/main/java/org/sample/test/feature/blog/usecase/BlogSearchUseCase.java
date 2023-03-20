package org.sample.test.feature.blog.usecase;

import lombok.RequiredArgsConstructor;
import org.sample.test.assets.enums.ErrorCode;
import org.sample.test.assets.exception.BusinessException;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogSearchUseCase implements BlogSearchInputBoundary {

    private static final String SEARCH_TARGET_KAKAO = "kakao";
    private static final String SEARCH_TARGET_NAVER = "naver";

    private final BlogSearchOutputBoundary outputBoundary;
    private final IBlogSearchDataProvider blogSearchDataProvider;

    @Override
    public BlogSearchResponse execute(BlogSearchRequest request) {
        // 1. validate request
        final String query = request.getQuery();
        if(!StringUtils.hasLength(query)) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "query is required");
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
        final BlogSearchResponse blogSearchResponse = toBlogSearchResponse(blogDocumentsDomain);
        return outputBoundary.getPresent(blogSearchResponse);
    }

    private BlogSearchResponse toBlogSearchResponse(BlogDocumentsDomain blogDocumentsDomain) {
        List<BlogSearchResponse.Document> documents = new ArrayList<>();
        for (BlogDocumentsDomain.Document blogDocument : blogDocumentsDomain.getDocuments()) {
            BlogSearchResponse.Document document = new BlogSearchResponse.Document();
            document.setTitle(blogDocument.getTitle());
            document.setLink(blogDocument.getLink());
            document.setDescription(blogDocument.getDescription());
            document.setBlogName(blogDocument.getBlogName());
            document.setPostDate(blogDocument.getPostDate());

            documents.add(document);
        }

        return BlogSearchResponse.builder()
                .total(blogDocumentsDomain.getTotal())
                .page(blogDocumentsDomain.getPage())
                .size(blogDocumentsDomain.getSize())
                .documents(documents)
                .build();
    }

    /*
    @Mapper(config = MapstructMapperConfig.class,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            uses = {DocumentMapper.class})
    public interface ResponseMapper {
        ResponseMapper MAPPER = Mappers.getMapper(ResponseMapper.class);

//        @Mappings({
//                @Mapping(source = "end", target = "end")
//        })
        BlogSearchResponse toBlogSearchResponse(BlogDocuments blogDocuments);

    }

    @Mapper(config = MapstructMapperConfig.class, unmappedSourcePolicy = ReportingPolicy.IGNORE)
    public interface DocumentMapper {
        BlogSearchResponse.Document toBlogDocument(BlogDocuments.Document document);
    }

     */


}
