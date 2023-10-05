package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class RememberedCardNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new RememberedCardNotFoundException();

    private RememberedCardNotFoundException() {
        super(HttpStatus.NOT_FOUND, "No cards found in your remember.");
    }
}
