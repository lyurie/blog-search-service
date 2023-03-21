package org.sample.test.feature.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class BlogDocumentsDomain {

    private Integer total;

    private Integer page;

    private Integer size;

    private List<Document> documents;

    @Getter
    @Builder
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
