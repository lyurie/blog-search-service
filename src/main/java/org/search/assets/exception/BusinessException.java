package org.search.assets.exception;

import lombok.Getter;
import org.search.assets.enums.ErrorCode;

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
