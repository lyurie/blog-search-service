package org.search.feature.blog.search.presenter;

import org.search.feature.blog.search.usecase.BlogSearchOutputBoundary;
import org.search.feature.blog.search.usecase.BlogSearchResponse;
import org.springframework.stereotype.Component;

@Component
public class BlogSearchPresenter implements BlogSearchOutputBoundary {

    @Override
    public BlogSearchResponse getPresent(final BlogSearchResponse blogSearchResponse) {
        return null;
    }

}
