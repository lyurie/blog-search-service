package org.sample.test.assets.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(1, "Internal Server Error"),
    INVALID_REQUEST(2, "Invalid Request");

    private int code;
    private String message;

}
