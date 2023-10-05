package com.dragonest.deepbackendcard.global.exception.handler;

import com.dragonest.deepbackendcard.global.exception.CustomException;
import com.dragonest.deepbackendcard.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new Response(e.getStatus().value(), e.getMessage()), e.getStatus());
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Response> handleMultipartException(MultipartException e) {
        return new ResponseEntity<>(new Response(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Response> handleException(Exception e) {
        return new ResponseEntity<>(new Response(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
