package com.dragonest.deepbackendcard.global.exception.handler;

import com.dragonest.deepbackendcard.global.enums.ResponseStatusType;
import com.dragonest.deepbackendcard.global.exception.CustomException;
import com.dragonest.deepbackendcard.global.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.dragonest.deepbackendcard.global.statics.response.ResponseMessageConstants.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new Response(
                e.getStatus().value(),
                errorMessageFormat(customStatusMessageFormat(ResponseStatusType.FAILURE, e.getStatus()), e.getMessage())
        ), e.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handlerNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.BAD_REQUEST.value(),
                errorMessageFormat(CLIENT_ERROR_BAD_REQUEST, e.getMessage())
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errorMessages = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                error -> errorMessages.put(
                        ((FieldError) error).getField(),
                        error.getDefaultMessage()
                )
        );
        return new ResponseEntity<>(new Response(
                HttpStatus.BAD_REQUEST.value(),
                errorMessageFormat(CLIENT_ERROR_BAD_REQUEST, errorMessages.toString())
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Response> handleMultipartException(MultipartException e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.BAD_REQUEST.value(),
                errorMessageFormat(CLIENT_ERROR_BAD_REQUEST, e.getMessage())
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<Response> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.BAD_REQUEST.value(),
                errorMessageFormat(CLIENT_ERROR_BAD_REQUEST, e.getMessage())
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Response> handleUnauthorizedException(Exception e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.UNAUTHORIZED.value(),
                errorMessageFormat(CLIENT_ERROR_UNAUTHORIZED, e.getMessage())
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Response> handleForbiddenException(Exception e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.FORBIDDEN.value(),
                errorMessageFormat(CLIENT_ERROR_FORBIDDEN, e.getMessage())
        ), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Response> handleInternalServerException(Exception e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorMessageFormat(SERVER_ERROR_INTERNAL_SERVER_ERROR, e.getMessage())
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGenericException(Exception e) {
        return new ResponseEntity<>(new Response(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorMessageFormat(SERVER_ERROR_INTERNAL_SERVER_ERROR, e.getMessage())
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
