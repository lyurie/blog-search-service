package org.sample.test.assets;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName("error")
public class ErrorResponse {
    private int code;
    private String name;
    private String message;
}
