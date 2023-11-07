package com.dragonest.deepbackendcard.domain.card.service;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.exception.CardNotCreateException;
import com.dragonest.deepbackendcard.domain.card.presentation.dto.request.CardRequest;
import com.dragonest.deepbackendcard.domain.card.domain.repository.CardRepository;
import com.dragonest.deepbackendcard.domain.card.exception.CardNotFoundException;
import com.dragonest.deepbackendcard.global.infra.RestRequest;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data.ValidateData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public ValidateData validate(String token) {
        return webClient.validate(userServerPath + "/v1/api/auth/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(CardRequest request, String token) {
        ValidateData user = validate(token);
        List<Card> cards = cardRepository.findAllByUid(user.getUserId()).orElseThrow(() -> CardNotFoundException.EXCEPTION);
        if (cards.size() >= 10) {
            throw CardNotCreateException.EXCEPTION;
        }
        cardRepository.save(request.toEntity(user.getUserId()));
    }

    public List<Card> getCards(String token) {
        ValidateData user = validate(token);
        return cardRepository.findAllByUid(user.getUserId()).orElseThrow(() -> CardNotFoundException.EXCEPTION);
    }

    public Card getCard(String id) {
        return cardRepository.findById(Long.valueOf(id)).orElseThrow(() -> CardNotFoundException.EXCEPTION);
    }

    public void update(CardRequest request, String id, String token) {
        ValidateData user = validate(token);
        Card card = cardRepository.findById(Long.valueOf(id)).orElseThrow(() -> CardNotFoundException.EXCEPTION);
        card.update(request.getTemplate(), request.getName(), request.getPosition(), request.getDepartment(), request.getPhone(), request.getEmail(), request.getGithub());
        cardRepository.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id, String token) {
        ValidateData user = validate(token);
        cardRepository.deleteById(Long.valueOf(id));
    }
}
