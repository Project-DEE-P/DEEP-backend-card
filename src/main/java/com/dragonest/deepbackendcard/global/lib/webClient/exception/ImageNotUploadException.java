package com.dragonest.deepbackendcard.global.lib.webClient.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ImageNotUploadException extends CustomException {

    public static final void EXCEPTION(int code, String message) {
        new ImageNotUploadException(code, message);
    }

    private ImageNotUploadException(int code, String message) {
        super(HttpStatus.valueOf(code), message);
    }
}
