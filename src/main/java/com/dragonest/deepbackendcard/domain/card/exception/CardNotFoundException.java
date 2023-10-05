package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CardNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new CardNotFoundException();

    private CardNotFoundException() {
        super(HttpStatus.NOT_FOUND, "The card could not be found.");
    }
}
