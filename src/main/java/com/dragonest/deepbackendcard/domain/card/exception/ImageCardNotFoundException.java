package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ImageCardNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new ImageCardNotFoundException();

    private ImageCardNotFoundException() {
        super(HttpStatus.NOT_FOUND, "The image card could not be found.");
    }
}
