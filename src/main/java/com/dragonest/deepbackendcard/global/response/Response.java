package com.dragonest.deepbackendcard.global.response;

import lombok.Getter;

@Getter
public class Response {

    private final int code;
    private final String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
