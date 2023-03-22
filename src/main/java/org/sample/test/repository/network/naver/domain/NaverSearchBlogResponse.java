package org.sample.test.repository.network.naver.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class NaverSearchBlogResponse {

    private Date lastBuildDate;

    private Integer total;

    private Integer start;

    private Integer display;

    private List<Item> items;

    @Builder(toBuilder = true)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
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
