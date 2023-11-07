package com.dragonest.deepbackendcard.global.lib.webClient.dto.response;

import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data.ImageData;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUploadResponse {

    private int code;
    private String message;
    private ImageData data;
}
