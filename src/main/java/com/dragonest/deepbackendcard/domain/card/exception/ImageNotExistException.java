package com.dragonest.deepbackendcard.domain.card.exception;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ImageNotExistException extends CustomException {

    public static final CustomException EXCEPTION = new ImageNotExistException();

    private ImageNotExistException() {
        super(HttpStatus.BAD_REQUEST, "Image does not exist.");
    }
}
