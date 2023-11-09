package com.dragonest.deepbackendcard.domain.remember.presentation;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import com.dragonest.deepbackendcard.domain.remember.presentation.dto.request.CreateRememberRequest;
import com.dragonest.deepbackendcard.domain.remember.service.RememberService;
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
@RequestMapping("/v2/api/remember")
public class RememberController {

    private final RememberService rememberService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@Valid @RequestBody CreateRememberRequest request, @RequestHeader("Authorization") String token) {
        rememberService.create(request, token);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("/template")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<Card>> getRememberTemplateCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, rememberService.getRememberTemplateCards(token));
    }

    @GetMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<ImageCard>> getRememberImageCards(@RequestHeader("Authorization") String token) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, rememberService.getRememberImageCards(token));
    }

    @DeleteMapping("/template/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> deleteRememberTemplateCard(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        rememberService.deleteRememberTemplateCard(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/image/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> deleteRememberImageCard(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        rememberService.deleteRememberImageCard(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
