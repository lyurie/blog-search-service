package org.sample.test.repository.network.kakao.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Getter
@Builder
public class KakaoSearchBlogResponse {

    private Meta meta;
    private List<Document> documents;

    @Getter
    @Builder
    public static class Meta {
        @JsonProperty("total_count")
        private Integer totalCount;

        @JsonProperty("pageable_count")
        private Integer pageableCount;

        @JsonProperty("is_end")
        private Boolean isEnd;
    }

    @Getter
    @Builder
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
