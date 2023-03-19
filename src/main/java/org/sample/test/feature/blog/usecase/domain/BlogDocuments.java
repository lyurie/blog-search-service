package org.sample.test.feature.blog.usecase.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class BlogDocuments {

    private Integer total;

    private Integer page;

    private Integer size;

    private List<Document> documents;

    @NoArgsConstructor
    @Data
    public static class Document {
        private String title;
        private String link;
        private String description;
        @JsonProperty("blog_name")
        private String blogName;
        @JsonProperty("post_date")
        private String postDate;
    }

}
