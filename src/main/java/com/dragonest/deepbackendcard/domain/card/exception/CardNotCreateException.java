package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class CardNotCreateException extends CustomException {

    public static final CustomException EXCEPTION = new CardNotCreateException();

    private CardNotCreateException() {
        super(HttpStatus.NOT_FOUND, "[BETA] Cannot create more than 10 business cards.");
    }
}
