package org.search.repository.network.kakao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
public class KakaoSearchBlogResponse {

    private Meta meta;
    private List<Document> documents;

    @NoArgsConstructor
    @Data
    public static class Meta {
        @JsonProperty("total_count")
        private Integer totalCount;

        @JsonProperty("pageable_count")
        private Integer pageableCount;

        @JsonProperty("is_end")
        private Boolean isEnd;
    }

    @NoArgsConstructor
    @Data
    public static class Document {
        @JsonProperty("title")
        private String title;

        @JsonProperty("contents")
        private String contents;

        @JsonProperty("url")
        private String url;

        @JsonProperty("blogname")
        private String blogName;

        @JsonProperty("thumbnail")
        private String thumbnail;

        @JsonProperty("datetime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME )
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private Date dateTime;
    }

}
