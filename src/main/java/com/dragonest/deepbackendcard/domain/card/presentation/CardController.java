package com.dragonest.deepbackendcard.domain.card.presentation;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.presentation.dto.request.CardRequest;
import com.dragonest.deepbackendcard.domain.card.service.CardService;
import com.dragonest.deepbackendcard.global.response.ResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dragonest.deepbackendcard.global.statics.response.ResponseMessageConstants.SUCCESSFUL_CREATED;
import static com.dragonest.deepbackendcard.global.statics.response.ResponseMessageConstants.SUCCESSFUL_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/api/card/template")
public class CardController {

    private final CardService cardService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@Valid @RequestBody CardRequest request, @RequestHeader("Authorization") String token) {
        cardService.create(request, token);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<Card>> getCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, cardService.getCards(token));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Card> getCard(@PathVariable("id") String id) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, cardService.getCard(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> update(@Valid @RequestBody CardRequest request, @PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.update(request, id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        cardService.delete(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
