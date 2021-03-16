package com.finartz.airline.rest.response;

import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.util.TranslateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    private String responseMessage;

    private T body;


    //success response message with body
    public static <T> ApiResponse<T> of(T body) {
        return ApiResponse.<T>builder()
                .responseMessage(TranslateUtils.getMessage(ResponseCode.SUCCESS.code().toString()))
                .body(body)
                .build();
    }

    //Custom response message with body
    public static <T> ApiResponse<T> of(T body, ResponseCode responseCode, String... args) {
        return ApiResponse.<T>builder()
                .responseMessage(TranslateUtils.getMessage(responseCode.code().toString(), args))
                .body(body)
                .build();
    }

    //success response message with no body
    public static ApiResponse success() {
        return ApiResponse.builder()
                .responseMessage(TranslateUtils.getMessage(ResponseCode.SUCCESS.code().toString()))
                .build();
    }

    //custom response message with no body
    public static ApiResponse success(ResponseCode responseCode, String... args) {
        return ApiResponse.builder()
                .responseMessage(TranslateUtils.getMessage(responseCode.code().toString(), args))
                .build();
    }

    //error response message with no body
    public static ApiResponse error() {
        return ApiResponse.builder()
                .responseMessage(TranslateUtils.getMessage(ResponseCode.INTERNAL_SERVER_ERROR.code().toString()))
                .build();
    }

}
