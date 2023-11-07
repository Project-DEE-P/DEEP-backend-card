package com.dragonest.deepbackendcard.global.statics.response;

import com.dragonest.deepbackendcard.global.enums.ResponseStatusType;
import org.springframework.http.HttpStatus;

public class ResponseMessageConstants {

    private ResponseMessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static String customStatusMessageFormat(ResponseStatusType statusType, HttpStatus status) {
        return String.format("%s %s", statusType.getValue(), status.getReasonPhrase());
    }

    public static String errorMessageFormat(String statusMessage, String errorMessage) {
        return String.format("%s :: %s", statusMessage, errorMessage);
    }


    public static final String SUCCESSFUL_CREATED = customStatusMessageFormat(ResponseStatusType.SUCCESS, HttpStatus.CREATED);
    public static final String SUCCESSFUL_OK = customStatusMessageFormat(ResponseStatusType.SUCCESS, HttpStatus.OK);


    public static final String CLIENT_ERROR_BAD_REQUEST = customStatusMessageFormat(ResponseStatusType.FAILURE, HttpStatus.BAD_REQUEST);
    public static final String CLIENT_ERROR_UNAUTHORIZED = customStatusMessageFormat(ResponseStatusType.FAILURE, HttpStatus.UNAUTHORIZED);
    public static final String CLIENT_ERROR_FORBIDDEN = customStatusMessageFormat(ResponseStatusType.FAILURE, HttpStatus.FORBIDDEN);
    public static final String SERVER_ERROR_INTERNAL_SERVER_ERROR = customStatusMessageFormat(ResponseStatusType.FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
}
