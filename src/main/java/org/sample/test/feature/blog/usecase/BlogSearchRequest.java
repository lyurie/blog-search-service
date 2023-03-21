package org.sample.test.feature.blog.usecase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogSearchRequest {

    private String query;

    private String sort;

    private int page;

    private int size;

    private String target;

}
