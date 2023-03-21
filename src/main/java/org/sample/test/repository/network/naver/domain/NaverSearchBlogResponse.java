package org.sample.test.repository.network.naver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class NaverSearchBlogResponse {

    private Date lastBuildDate;

    private Integer total;

    private Integer start;

    private Integer display;

    private List<Item> items;

    @Getter
    @Builder
    public static class Item {

        private String title;

        private String link;

        private String description;

        @JsonProperty("bloggername")
        private String bloggerName;

        @JsonProperty("bloggerlink")
        private String bloggerLink;

        @JsonProperty("postdate")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE )
        @JsonFormat(pattern = "yyyyMMdd")
        private Date postDate;

    }

}
