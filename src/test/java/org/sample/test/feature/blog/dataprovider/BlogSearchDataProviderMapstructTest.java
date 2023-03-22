package org.sample.test.feature.blog.dataprovider;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;
import org.sample.test.repository.network.kakao.domain.KakaoSearchBlogResponse;
import org.sample.test.repository.network.naver.domain.NaverSearchBlogResponse;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BlogSearchDataProviderMapstructTest {

    private BlogSearchDataProvider$KakaoDocumentMapperImpl kakaoDocumentMapper;
    private BlogSearchDataProvider$KakaoResponseMapperImpl kakaoResponseMapper;
    private BlogSearchDataProvider$NaverItemMapperImpl naverItemMapper;
    private BlogSearchDataProvider$NaverResponseMapperImpl naverResponseMapper;

    private KakaoSearchBlogResponse.Document kakaoResponseDocument;
    private KakaoSearchBlogResponse kakaoResponse;
    private NaverSearchBlogResponse.Item naverResponseItem;
    private NaverSearchBlogResponse naverResponse;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    void beforeAll() {
        kakaoDocumentMapper = new BlogSearchDataProvider$KakaoDocumentMapperImpl();
        kakaoResponseMapper = new BlogSearchDataProvider$KakaoResponseMapperImpl(kakaoDocumentMapper);
        naverItemMapper = new BlogSearchDataProvider$NaverItemMapperImpl();
        naverResponseMapper = new BlogSearchDataProvider$NaverResponseMapperImpl(naverItemMapper);

        kakaoResponseDocument = KakaoSearchBlogResponse.Document.builder()
            .title("title")
            .contents("contents")
            .url("url")
            .blogName("blogName")
            .thumbnail("thumbnail")
            .dateTime(new Date())
            .build();

        kakaoResponse = KakaoSearchBlogResponse.builder()
            .meta(
                KakaoSearchBlogResponse.Meta.builder()
                    .totalCount(100)
                    .pageableCount(10)
                    .isEnd(false)
                    .build())
            .documents(Arrays.asList(kakaoResponseDocument))
            .build();

        naverResponseItem = NaverSearchBlogResponse.Item.builder()
            .title("title")
            .link("link")
            .description("description")
            .bloggerName("bloggerName")
            .bloggerLink("bloggerLink")
            .postDate(new Date())
            .build();

        naverResponse = NaverSearchBlogResponse.builder()
            .lastBuildDate(new Date())
            .total(100)
            .start(0)
            .display(10)
            .items(Arrays.asList(naverResponseItem))
            .build();
    }

    @Test
    void blogSearchDataProvider_kakao_response_mapper_test() {
        // given
        int page = 1;
        int size = 10;

        // when
        BlogDocumentsDomain domain = kakaoResponseMapper.toDomain(kakaoResponse, page, size);

        // then
        assertThat(domain.getTotal() == kakaoResponse.getMeta().getTotalCount());
        assertThat(domain.getPage() == page);
        assertThat(domain.getSize() == size);
        assertThat((domain.getDocuments()))
            .extracting("title", "link", "description", "blogName", "postDate")
            .containsExactly(
                tuple(
                    kakaoResponseDocument.getTitle(),
                    kakaoResponseDocument.getUrl(),
                    kakaoResponseDocument.getContents(),
                    kakaoResponseDocument.getBlogName(),
                    dateFormat.format(kakaoResponseDocument.getDateTime())
                )
            );
    }

    @Test
    void blogSearchDataProvider_kakao_document_mapper_test() {
        // given

        // when
        BlogDocumentsDomain.Document domainDocument = kakaoDocumentMapper.toDocument(kakaoResponseDocument);

        // then
        assertThat(domainDocument.getTitle().equals(kakaoResponseDocument.getTitle()));
        assertThat(domainDocument.getLink().equals(kakaoResponseDocument.getUrl()));
        assertThat(domainDocument.getDescription().equals(kakaoResponseDocument.getContents()));
        assertThat(domainDocument.getBlogName().equals(kakaoResponseDocument.getBlogName()));
        assertThat(domainDocument.getPostDate().equals(dateFormat.format(kakaoResponseDocument.getDateTime())));
    }

    @Test
    void blogSearchDataProvider_naver_response_mapper_test() {
        // given
        int page = 1;
        int size = 10;

        // when
        BlogDocumentsDomain domain = naverResponseMapper.toDomain(naverResponse, page, size);

        // then
        assertThat(domain.getTotal() == naverResponse.getTotal());
        assertThat(domain.getPage() == page);
        assertThat(domain.getSize() == size);
        assertThat((domain.getDocuments()))
            .extracting("title", "link", "description", "blogName", "postDate")
            .containsExactly(
                tuple(
                    naverResponseItem.getTitle(),
                    naverResponseItem.getLink(),
                    naverResponseItem.getDescription(),
                    naverResponseItem.getBloggerName(),
                    dateFormat.format(naverResponseItem.getPostDate())
                )
            );
    }

    @Test
    void blogSearchDataProvider_naver_document_mapper_test() {
        // given

        // when
        BlogDocumentsDomain.Document domainDocument = naverItemMapper.toDocument(naverResponseItem);

        // then
        assertThat(domainDocument.getTitle().equals(naverResponseItem.getTitle()));
        assertThat(domainDocument.getLink().equals(naverResponseItem.getLink()));
        assertThat(domainDocument.getDescription().equals(naverResponseItem.getDescription()));
        assertThat(domainDocument.getBlogName().equals(naverResponseItem.getBloggerName()));
        assertThat(domainDocument.getPostDate().equals(dateFormat.format(naverResponseItem.getPostDate())));
    }

}
