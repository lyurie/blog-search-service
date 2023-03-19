package org.sample.test.feature.blog.presenter;

import org.sample.test.feature.blog.usecase.BlogSearchOutputBoundary;
import org.sample.test.feature.blog.usecase.BlogSearchResponse;
import org.springframework.stereotype.Component;

@Component
public class BlogSearchPresenter implements BlogSearchOutputBoundary {

    @Override
    public BlogSearchResponse getPresent(final BlogSearchResponse blogSearchResponse) {
        return blogSearchResponse;
    }

}
