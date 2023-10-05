package com.dragonest.deepbackendcard.domain.card.service;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.Remember;
import com.dragonest.deepbackendcard.domain.card.domain.repository.CardRepository;
import com.dragonest.deepbackendcard.domain.card.domain.repository.RememberRepository;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ImageUploadResponse;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ValidatedUser;
import com.dragonest.deepbackendcard.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private CardRepository cardRepository;
    private RememberRepository rememberRepository;
    private final WebClientUtil webClientUtil;

    @Value(value = "${web.path}")
    private String webPath;

    @Value(value = "${web.cardView.path}")
    private String cardViewPath;

    @Transactional(rollbackFor = Exception.class)
    public String createCard(MultipartFile cardImage, ValidatedUser validatedUser) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipart-file-image", cardImage.getResource());
        ImageUploadResponse imageUploadResponse = webClientUtil.imageUpload(builder);
        Card card = cardRepository.save(Card.builder()
                .uid(validatedUser.getUid())
                .imagePath(webClientUtil.getImageServerPath() + "/api/siss/storages/images/" + imageUploadResponse.getData().getId())
                .build());
        return webPath + cardViewPath + card.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void rememberCard(String id, ValidatedUser validatedUser) {
        rememberRepository.save(Remember.builder()
                .uid(validatedUser.getUid())
                .cardId(id)
                .build());
    }

    public List<Remember> getRememberedCardsWithUser(ValidatedUser validatedUser) {
        return rememberRepository.findAllByUid(validatedUser.getUid()).get();
    }

    public Card getCard(String id, ValidatedUser validatedUser) {
        return cardRepository.findById(Long.valueOf(id)).get();
    }

    public List<Card> getCardsWithUser(ValidatedUser validatedUser) {
        return cardRepository.findAllByUid(validatedUser.getUid()).get();
    }

    public String cardShare(String id, ValidatedUser validatedUser) {
        return webPath + cardViewPath + id;
    }

    public void updateCard(MultipartFile cardImage, String id, ValidatedUser validatedUser) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipart-file-image", cardImage.getResource());
        ImageUploadResponse imageUploadResponse = webClientUtil.imageUpload(builder);
        Card card = cardRepository.findById(Long.valueOf(id)).get();
        card.setImagePath(webClientUtil.getImageServerPath() + "/api/siss/storages/images/" + imageUploadResponse.getData().getId());
        cardRepository.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRemember(String id, ValidatedUser validatedUser) {
        rememberRepository.deleteById(Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCard(String id, ValidatedUser validatedUser) {
        cardRepository.deleteById(Long.valueOf(id));
    }
}
