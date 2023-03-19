package org.search.configuration.advice;

import org.search.assets.ErrorResponse;
import org.search.assets.enums.ErrorCode;
import org.search.assets.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//        final ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
//        final ErrorResponse errorResponse = ErrorResponse.builder()
//                .code(errorCode.getCode())
//                .name(errorCode.name())
//                .message(e.getMessage())
//                .build();
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
//        final ErrorCode errorCode = e.getErrorCode();
//        final ErrorResponse errorResponse = ErrorResponse.builder()
//                .code(errorCode.getCode())
//                .name(errorCode.name())
//                .message(e.getMessage())
//                .build();
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }

}
