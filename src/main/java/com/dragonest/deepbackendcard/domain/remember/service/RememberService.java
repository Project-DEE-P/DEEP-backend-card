package com.dragonest.deepbackendcard.domain.remember.service;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import com.dragonest.deepbackendcard.domain.card.domain.repository.CardRepository;
import com.dragonest.deepbackendcard.domain.card.domain.repository.ImageCardRepository;
import com.dragonest.deepbackendcard.domain.card.enums.CardType;
import com.dragonest.deepbackendcard.domain.card.exception.CardNotFoundException;
import com.dragonest.deepbackendcard.domain.card.exception.ImageCardNotFoundException;
import com.dragonest.deepbackendcard.domain.remember.exception.RememberNotFoundException;
import com.dragonest.deepbackendcard.domain.remember.domain.Remember;
import com.dragonest.deepbackendcard.domain.remember.domain.repository.RememberRepository;
import com.dragonest.deepbackendcard.domain.remember.presentation.dto.request.CreateRememberRequest;
import com.dragonest.deepbackendcard.global.infra.RestRequest;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data.ValidateData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RememberService {

    private final CardRepository cardRepository;
    private final ImageCardRepository imageCardRepository;
    private final RememberRepository rememberRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public ValidateData validate(String token) {
        return webClient.validate(userServerPath + "/v1/api/auth/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(CreateRememberRequest request, String token) {
        ValidateData user = validate(token);
        rememberRepository.save(request.toEntity(user.getUserId()));
    }

    public List<Card> getRememberTemplateCards(String token) {
        ValidateData user = validate(token);
        List<Remember> remembers = rememberRepository.findAllByUserAndCardType(user.getUserId(), CardType.TEMPLATE).orElseThrow(() -> RememberNotFoundException.EXCEPTION);
        List<Card> cards = new ArrayList<>();
        for (Remember remember : remembers) {
            cards.add(cardRepository.findById(remember.getCard()).orElseThrow(() -> CardNotFoundException.EXCEPTION));
        }
        return cards;
    }

    public List<ImageCard> getRememberImageCards(String token) {
        ValidateData user = validate(token);
        List<Remember> remembers = rememberRepository.findAllByUserAndCardType(user.getUserId(), CardType.IMAGE).orElseThrow(() -> RememberNotFoundException.EXCEPTION);
        List<ImageCard> imageCards = new ArrayList<>();
        for (Remember remember : remembers) {
            imageCards.add(imageCardRepository.findById(remember.getCard()).orElseThrow(() -> ImageCardNotFoundException.EXCEPTION));
        }
        return imageCards;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRememberTemplateCard(String id, String token) {
        ValidateData user = validate(token);
        Remember remember = rememberRepository.findAllByUserAndCardTypeAndCard(user.getUserId(), CardType.TEMPLATE, Long.valueOf(id)).orElseThrow(() -> RememberNotFoundException.EXCEPTION);
        rememberRepository.deleteById(Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRememberImageCard(String id, String token) {
        ValidateData user = validate(token);
        Remember remember = rememberRepository.findAllByUserAndCardTypeAndCard(user.getUserId(), CardType.IMAGE, Long.valueOf(id)).orElseThrow(() -> RememberNotFoundException.EXCEPTION);
        rememberRepository.deleteById(Long.valueOf(id));
    }
}