package org.sample.test.repository.network.kakao.domain;

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
public class KakaoSearchBlogResponse {

    private Meta meta;
    private List<Document> documents;

    @Builder(toBuilder = true)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class Meta {
        @JsonProperty("total_count")
        private Integer totalCount;

        @JsonProperty("pageable_count")
        private Integer pageableCount;

        @JsonProperty("is_end")
        private Boolean isEnd;
    }

    @Builder(toBuilder = true)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class Document {

        private String title;

        private String contents;

        private String url;

        @JsonProperty("blogname")
        private String blogName;

        private String thumbnail;

        @JsonProperty("datetime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME )
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        private Date dateTime;
    }

}
