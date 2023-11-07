package com.dragonest.deepbackendcard.domain.card.presentation;

import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import com.dragonest.deepbackendcard.domain.card.presentation.dto.request.ImageCardRequest;
import com.dragonest.deepbackendcard.domain.card.service.ImageCardService;
import com.dragonest.deepbackendcard.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.dragonest.deepbackendcard.global.statics.response.ResponseMessageConstants.SUCCESSFUL_CREATED;
import static com.dragonest.deepbackendcard.global.statics.response.ResponseMessageConstants.SUCCESSFUL_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/api/card/image")
public class ImageCardController {

    private final ImageCardService imageCardService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@RequestPart("request") ImageCardRequest request, @RequestPart("image") MultipartFile image, @RequestHeader("Authorization") String token) {
        imageCardService.create(request, image, token);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<ImageCard>> getCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, imageCardService.getCards(token));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<ImageCard> getCard(@PathVariable("id") String id) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, imageCardService.getCard(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> update(@RequestBody ImageCardRequest request, @PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        imageCardService.update(request, id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        imageCardService.delete(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
