package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyRememberCardException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyRememberCardException();

    private AlreadyRememberCardException() {
        super(HttpStatus.BAD_REQUEST, "This is the business card I already remember.");
    }
}