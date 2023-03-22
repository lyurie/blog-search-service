package org.sample.test.feature.blog.usecase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sample.test.feature.blog.domain.BlogDocumentsDomain;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BlogSearchUseCaseMapstructTest {

    private BlogSearchUseCase$DocumentMapperImpl documentMapper;
    private BlogSearchUseCase$DomainMapperImpl domainMapper;

    private BlogDocumentsDomain.Document domainDocument;
    private BlogDocumentsDomain domain;

    @BeforeAll
    void beforeAll() {
        documentMapper = new BlogSearchUseCase$DocumentMapperImpl();
        domainMapper = new BlogSearchUseCase$DomainMapperImpl(documentMapper);

        domainDocument = BlogDocumentsDomain.Document.builder()
            .title("title")
            .link("link")
            .description("description")
            .blogName("blogName")
            .postDate("2023-03-22 00:00:00")
            .build();

        domain = BlogDocumentsDomain.builder()
            .total(100)
            .page(1)
            .size(10)
            .documents(Arrays.asList(domainDocument))
            .build();
    }

    @Test
    void blogSearchUseCase_response_mapper_test() {
        // given

        // when
        BlogSearchResponse response = domainMapper.toResponse(domain);

        // then
        assertThat(response.getTotal() == domain.getTotal());
        assertThat(response.getPage() == domain.getPage());
        assertThat(response.getSize() == domain.getSize());
        assertThat(response.getDocuments())
            .extracting("title", "link", "description", "blogName", "postDate")
            .containsExactly(
                tuple(
                    domainDocument.getTitle(),
                    domainDocument.getLink(),
                    domainDocument.getDescription(),
                    domainDocument.getBlogName(),
                    domainDocument.getPostDate()
                )
            );
    }

    @Test
    void blogSearchUseCase_document_mapper_test() {
        // given

        // when
        BlogSearchResponse.Document responseDocument = documentMapper.toDocument(domainDocument);

        // then
        assertThat(responseDocument.getTitle().equals(domainDocument.getTitle()));
        assertThat(responseDocument.getLink().equals(domainDocument.getLink()));
        assertThat(responseDocument.getDescription().equals(domainDocument.getDescription()));
        assertThat(responseDocument.getBlogName().equals(domainDocument.getBlogName()));
        assertThat(responseDocument.getPostDate().equals(domainDocument.getPostDate()));
    }

}
