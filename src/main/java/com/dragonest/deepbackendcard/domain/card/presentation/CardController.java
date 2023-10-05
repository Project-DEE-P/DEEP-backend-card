package com.dragonest.deepbackendcard.domain.card.presentation;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.Remember;
import com.dragonest.deepbackendcard.domain.card.service.CardService;
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

    private CardService cardService;
    private WebClientUtil webClientUtil;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestParam("card-image") MultipartFile cardImage, @RequestHeader("Authorization") String token) {
        return cardService.createCard(cardImage, webClientUtil.validate(token));
    }

    @PostMapping("/{id}/remember")
    @ResponseStatus(HttpStatus.CREATED)
    public void remember(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.rememberCard(id, webClientUtil.validate(token));
    }

    @GetMapping("/remember")
    @ResponseStatus(HttpStatus.OK)
    public List<Remember> getRememberedCards(@RequestHeader("Authorization") String token) {
        return cardService.getRememberedCardsWithUser(webClientUtil.validate(token));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Card getCard(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        return cardService.getCard(id, webClientUtil.validate(token));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Card> getMyCards(@RequestHeader("Authorization") String token) {
        return cardService.getCardsWithUser(webClientUtil.validate(token));
    }

    @GetMapping("/{id}/share")
    @ResponseStatus(HttpStatus.OK)
    public String getCardShareLink(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        return cardService.cardShare(id, webClientUtil.validate(token));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestParam("card-image") MultipartFile cardImage, @PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.updateCard(cardImage, id, webClientUtil.validate(token));
    }

    @DeleteMapping("/remember/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRemember(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.deleteRemember(id, webClientUtil.validate(token));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.deleteCard(id, webClientUtil.validate(token));
    }
}
