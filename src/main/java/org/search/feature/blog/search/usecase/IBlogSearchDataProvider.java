package org.search.feature.blog.search.usecase;

import org.search.feature.blog.search.usecase.domain.BlogDocuments;

public interface IBlogSearchDataProvider {

    BlogDocuments search(String query, String sort, int page, int size);

}
