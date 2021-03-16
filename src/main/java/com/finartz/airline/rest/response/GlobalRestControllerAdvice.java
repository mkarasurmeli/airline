package com.finartz.airline.rest.response;

import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.rest.exception.ApiException;
import com.finartz.airline.util.TranslateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> handleBaseRuntimeException(ApiException ex) {

        log.error("Api exception occured:", ex);// ex.stacktrace filtered halini bastır

        return ResponseEntity.status(ex.getResponseCode().httpStatus())
                .body(ApiResponse.builder()
                        .responseMessage(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {

        log.error("Exception occured:", ex);

        return ResponseEntity.status(ResponseCode.INTERNAL_SERVER_ERROR.httpStatus())
                .body(ApiResponse.error());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Method argument not valid:", ex);

        StringBuilder responseMessage = new StringBuilder();
        AtomicReference<String> columnName = new AtomicReference<>("");
        ex.getBindingResult().getAllErrors().forEach(objectError -> {

            columnName.set(((FieldError) objectError).getField());

            responseMessage.append(columnName.get()).append(" : ").append(TranslateUtils.getMessage(objectError.getCode())).append("\n");

        });
        return ResponseEntity.status(ResponseCode.BAD_REQUEST.httpStatus())
                .body(ApiResponse.builder()
                        .responseMessage(responseMessage.toString())
                        .build()
                );
    }

}
