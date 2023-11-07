package com.dragonest.deepbackendcard.global.lib.webClient.dto.response;

import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data.ValidateData;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateResponse {

    private int statusCode;
    private String message;
    private ValidateData data;
}
