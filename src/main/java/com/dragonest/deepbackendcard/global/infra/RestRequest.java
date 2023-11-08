package com.dragonest.deepbackendcard.global.infra;

import com.dragonest.deepbackendcard.global.config.webClient.WebClientConfig;
import com.dragonest.deepbackendcard.global.exception.global.ExternalAPIException;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ImageUploadResponse;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ValidateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;

@Component
@RequiredArgsConstructor
public class RestRequest {

    private final WebClientConfig webClientConfig;

    public ValidateResponse validate(String url, String token) {
        ResponseEntity<ValidateResponse> response = webClientConfig.webClient().method(HttpMethod.POST)
                .uri(url)
                .header("Authorization", token)
                .retrieve()
                .toEntity(ValidateResponse.class)
                .block();
        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw ExternalAPIException.EXCEPTION;
        }
        return response.getBody();
    }

//    public ImageUploadResponse imageUpload(String url, String accessKey, MultipartBodyBuilder imageBuilder) {
//        ResponseEntity<ImageUploadResponse> response =  webClientConfig.webClient().method(HttpMethod.POST)
//                .uri(url)
//                .header("ACCESS-KEY", accessKey)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .body(BodyInserters.fromMultipartData(imageBuilder.build()))
//                .retrieve()
//                .toEntity(ImageUploadResponse.class)
//                .block();
//        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
////            throw ExternalAPIException.EXCEPTION;
//            throw new ExternalAPIException(response.getBody().getMessage());
//        }
//        return response.getBody();
//    }
//
//    public ImageUploadResponse removeImage(String url, String accessKey) {
//        ResponseEntity<ImageUploadResponse> response =  webClientConfig.webClient().method(HttpMethod.DELETE)
//                .uri(url)
//                .header("ACCESS-KEY", accessKey)
//                .retrieve()
//                .toEntity(ImageUploadResponse.class)
//                .block();
//        if (response == null || response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
//            throw ExternalAPIException.EXCEPTION;
//        }
//        return response.getBody();
//    }
}
