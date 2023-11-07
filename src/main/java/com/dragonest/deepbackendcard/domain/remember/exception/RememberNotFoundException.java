package com.dragonest.deepbackendcard.domain.remember.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class RememberNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new RememberNotFoundException();

    private RememberNotFoundException() {
        super(HttpStatus.NOT_FOUND, "No cards found in your remember.");
    }
}
