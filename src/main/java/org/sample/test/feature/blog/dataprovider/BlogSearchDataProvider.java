package org.sample.test.feature.blog.dataprovider;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.sample.test.feature.blog.usecase.IBlogSearchDataProvider;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;
import org.sample.test.repository.network.kakao.KakaoRestAPIRepository;
import org.sample.test.repository.network.kakao.domain.KakaoSearchBlogResponse;
import org.sample.test.repository.network.naver.NaverRestAPIRepository;
import org.sample.test.repository.network.naver.domain.NaverSearchBlogResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BlogSearchDataProvider implements IBlogSearchDataProvider {

    private final KakaoRestAPIRepository kakaoRestAPIRepository;
    private final NaverRestAPIRepository naverRestAPIRepository;

    @Override
    public BlogDocumentsDomain searchFromKakaoWithNoFallback(String query, String sort, int page, int size) {
        final KakaoSearchBlogResponse response = kakaoRestAPIRepository.searchBlog(query, sort, page, size);
        return toBlogDocument(page, size, response);
    }

    @CircuitBreaker(name = "searchBlog", fallbackMethod = "searchFromKakaoFallback")
    @Override
    public BlogDocumentsDomain searchFromKakaoWithFallback(String query, String sort, int page, int size) {
        final KakaoSearchBlogResponse response = kakaoRestAPIRepository.searchBlog(query, sort, page, size);
        return toBlogDocument(page, size, response);
    }

    @Override
    public BlogDocumentsDomain searchFromNaverWithNoFallback(String query, String sort, int page, int size) {
        final NaverSearchBlogResponse response = naverRestAPIRepository.searchBlog(query, size, (page - 1) * size + 1, sort.equals("accuracy") ? "sim" : "date");
        return toBlogDocument(page, size, response);
    }

    public BlogDocumentsDomain searchFromKakaoFallback(String query, String sort, int page, int size, Throwable t) {
        final NaverSearchBlogResponse response = naverRestAPIRepository.searchBlog(query, size, (page - 1) * size + 1, sort.equals("accuracy") ? "sim" : "date");
        return toBlogDocument(page, size, response);
    }


    private BlogDocumentsDomain toBlogDocument(int page, int size, KakaoSearchBlogResponse response) {
        List<BlogDocumentsDomain.Document> documents = new ArrayList<>();
        for (KakaoSearchBlogResponse.Document responseDocument : response.getDocuments()) {
            BlogDocumentsDomain.Document document = new BlogDocumentsDomain.Document();
            document.setTitle(responseDocument.getTitle());
            document.setLink(responseDocument.getUrl());
            document.setDescription(responseDocument.getContents());
            document.setBlogName(responseDocument.getBlogName());
            document.setPostDate(responseDocument.getDateTime().toString());
            documents.add(document);
        }

        return BlogDocumentsDomain.builder()
                .total(response.getMeta().getTotalCount())
                .page(page)
                .size(size)
                .documents(documents)
                .build();
    }

    private BlogDocumentsDomain toBlogDocument(int page, int size, NaverSearchBlogResponse response) {
        List<BlogDocumentsDomain.Document> documents = new ArrayList<>();
        for (NaverSearchBlogResponse.Item item : response.getItems()) {
            BlogDocumentsDomain.Document document = new BlogDocumentsDomain.Document();
            document.setTitle(item.getTitle());
            document.setLink(item.getLink());
            document.setDescription(item.getDescription());
            document.setBlogName(item.getBloggerName());
            document.setPostDate(item.getPostDate());
            documents.add(document);
        }

        return BlogDocumentsDomain.builder()
                .total(response.getTotal())
                .page(page)
                .size(size)
                .documents(documents)
                .build();
    }

    /*
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
     */


}
