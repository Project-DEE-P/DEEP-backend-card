package com.dragonest.deepbackendcard.global.response;

import lombok.Getter;

@Getter
public class ResponseData <T> extends Response {

    private final T data;

    private ResponseData(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> ResponseData<T> of(int code, String message, T data) {
        return new ResponseData<>(code, message, data);
    }

    public static <T> ResponseData<T> of(int code, String message) {
        return new ResponseData<>(code, message, null);
    }
}
