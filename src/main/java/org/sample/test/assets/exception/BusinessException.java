package org.sample.test.assets.exception;

import lombok.Getter;
import org.sample.test.assets.enums.ErrorCode;

public class BusinessException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
