package com.dragonest.deepbackendcard.global.util;

import com.dragonest.deepbackendcard.global.config.webClient.WebClientConfig;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ImageUploadResponse;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ValidatedResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
@Getter
public class WebClientUtil {

    private final WebClientConfig webClientConfig;

    @Value(value = "${webClient.servers.imageServer.path}")
    private String imageServerPath;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public ImageUploadResponse imageUpload(MultipartBodyBuilder imageBuilder) {
        return webClientConfig.webClient().method(HttpMethod.POST)
                .uri(imageServerPath + "/api/siss/storages/images/image")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(imageBuilder.build()))
                .retrieve()
                .bodyToMono(ImageUploadResponse.class)
                .block();
    }

    public ValidatedResponse validate(String token) {
        return webClientConfig.webClient().method(HttpMethod.POST)
//                .uri(userServerPath + "/v1/api/auth/jwt/validate")
                .uri(userServerPath + "/v1/api/auth/validate")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(ValidatedResponse.class)
                .block();
    }
}
