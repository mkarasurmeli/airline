package com.finartz.airline.rest.exception;

import com.finartz.airline.rest.ResponseCode;
import com.finartz.airline.util.TranslateUtils;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private ResponseCode responseCode;
    private Object[] args;

    public ApiException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ApiException(ResponseCode responseCode, Object... args) {
        this.responseCode = responseCode;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return TranslateUtils.getMessage(this.getResponseCode().code().toString());
    }
}
