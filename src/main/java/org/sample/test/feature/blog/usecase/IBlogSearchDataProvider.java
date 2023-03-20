package org.sample.test.feature.blog.usecase;

import org.sample.test.feature.blog.domain.BlogDocumentsDomain;

public interface IBlogSearchDataProvider {

    BlogDocumentsDomain searchFromKakaoWithNoFallback(String query, String sort, int page, int size);
    BlogDocumentsDomain searchFromKakaoWithFallback(String query, String sort, int page, int size);
    BlogDocumentsDomain searchFromNaverWithNoFallback(String query, String sort, int page, int size);

}
