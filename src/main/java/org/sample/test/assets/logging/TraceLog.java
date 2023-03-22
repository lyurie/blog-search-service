package org.sample.test.assets.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TraceLog {

    @JsonProperty("dt")
    private String createdDate;
    private String ip;
    private String uri;
    private String method;
    private Object queryParams;
    private Object requestHeader;
    private Object requestBody;
    private int status;
    private Object responseHeader;
    private Object responseBody;

}
