package com.dragonest.deepbackendcard.global.exception.global;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExternalAPIException extends CustomException {

    public static final CustomException EXCEPTION = new ExternalAPIException();

    private ExternalAPIException() {
        super(HttpStatus.BAD_GATEWAY, "External API server error.");
    }
}
