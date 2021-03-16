package com.finartz.airline.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS(0, HttpStatus.OK),
    DATA_SAVE_SUCCESS(1, HttpStatus.OK),
    DATA_UPDATE_SUCCESS(2, HttpStatus.OK),
    DATA_DELETE_SUCCESS(3, HttpStatus.OK),
    // aranan kayıt bulunamamışsa
    DATA_NOT_FOUND(4, HttpStatus.CONFLICT),
    // iç sunucu hatası oluştuğu durumlarda sadece GlobalRestControllerAdvice sınıfında kullanacağız
    INTERNAL_SERVER_ERROR(5, HttpStatus.INTERNAL_SERVER_ERROR),
    // client tarafından gönderilen isteklerde yer alan hataları handle etmek için sadece GlobalRestControllerAdvice sınıfında kullanacağız
    BAD_REQUEST(6, HttpStatus.BAD_REQUEST);

    private final Integer code;
    private final HttpStatus httpStatus;

    public Integer code() {
        return code;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }


}
