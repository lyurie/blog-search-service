package org.sample.test.feature.blog.usecase;

import org.sample.test.feature.blog.usecase.domain.BlogDocuments;

public interface IBlogSearchDataProvider {

    BlogDocuments searchFromKakaoWithNoFallback(String query, String sort, int page, int size);
    BlogDocuments searchFromKakaoWithFallback(String query, String sort, int page, int size);
    BlogDocuments searchFromNaverWithNoFallback(String query, String sort, int page, int size);

}
