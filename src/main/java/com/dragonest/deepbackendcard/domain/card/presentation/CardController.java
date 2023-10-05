package com.dragonest.deepbackendcard.domain.card.presentation;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.Remember;
import com.dragonest.deepbackendcard.domain.card.service.CardService;
import com.dragonest.deepbackendcard.global.response.ResponseData;
import com.dragonest.deepbackendcard.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/card")
public class CardController {

    private final CardService cardService;
    private final WebClientUtil webClientUtil;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@RequestParam("card-image") MultipartFile cardImage, @RequestHeader("Authorization") String token) {
        cardService.createCard(cardImage, webClientUtil.validate(token));
        return ResponseData.of(201, "success");
    }

    @PostMapping("/{id}/remember")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> remember(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.rememberCard(id, webClientUtil.validate(token));
        return ResponseData.of(201, "success");
    }

    @GetMapping("/remember")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<Remember>> getRememberedCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(200, "success", cardService.getRememberedCardsWithUser(webClientUtil.validate(token)));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Card> getCard(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        return ResponseData.of(200, "success", cardService.getCard(id, webClientUtil.validate(token)));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<Card>> getMyCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(200, "success", cardService.getCardsWithUser(webClientUtil.validate(token)));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> update(@RequestParam("card-image") MultipartFile cardImage, @PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.updateCard(cardImage, id, webClientUtil.validate(token));
        return ResponseData.of(200, "success");
    }

    @DeleteMapping("/remember/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> deleteRemember(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.deleteRemember(id, webClientUtil.validate(token));
        return ResponseData.of(200, "success");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.deleteCard(id, webClientUtil.validate(token));
        return ResponseData.of(200, "success");
    }
}
