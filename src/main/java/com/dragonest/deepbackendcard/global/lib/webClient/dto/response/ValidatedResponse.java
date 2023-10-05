package com.dragonest.deepbackendcard.global.lib.webClient.dto.response;

import com.dragonest.deepbackendcard.global.lib.webClient.dto.ValidateData;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidatedResponse {

    private int statusCode;
    private String message;
    private ValidateData data;
}
