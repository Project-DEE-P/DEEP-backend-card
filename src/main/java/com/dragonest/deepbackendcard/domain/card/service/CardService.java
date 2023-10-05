package com.dragonest.deepbackendcard.domain.card.service;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import com.dragonest.deepbackendcard.domain.card.domain.Remember;
import com.dragonest.deepbackendcard.domain.card.domain.repository.CardRepository;
import com.dragonest.deepbackendcard.domain.card.domain.repository.RememberRepository;
import com.dragonest.deepbackendcard.domain.card.exception.CardNotFoundException;
import com.dragonest.deepbackendcard.domain.card.exception.ImageNotExistException;
import com.dragonest.deepbackendcard.domain.card.exception.RememberedCardNotFoundException;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ImageUploadResponse;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.ValidatedResponse;
import com.dragonest.deepbackendcard.global.lib.webClient.exception.ImageNotUploadException;
import com.dragonest.deepbackendcard.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final RememberRepository rememberRepository;
    private final WebClientUtil webClientUtil;

    @Transactional(rollbackFor = Exception.class)
    public void createCard(MultipartFile cardImage, ValidatedResponse validatedUser) {
        log.info("{}", cardImage);
        if (cardImage == null) throw ImageNotExistException.EXCEPTION;
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipart-file-image", cardImage.getResource());
        ImageUploadResponse imageUploadResponse = webClientUtil.imageUpload(builder);
        if (imageUploadResponse.getCode() / 100 != 2) ImageNotUploadException.EXCEPTION(imageUploadResponse.getCode(), imageUploadResponse.getMessage());
        cardRepository.save(Card.builder()
                .uid(validatedUser.getData().getUserId())
                .imagePath(webClientUtil.getImageServerPath() + "/api/siss/storages/images/" + imageUploadResponse.getData().getId())
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void rememberCard(String id, ValidatedResponse validatedUser) {
        rememberRepository.save(Remember.builder()
                .uid(validatedUser.getData().getUserId())
                .card(cardRepository.findById(Long.valueOf(id)).orElseThrow(() -> CardNotFoundException.EXCEPTION))
                .build());
    }

    public List<Remember> getRememberedCardsWithUser(ValidatedResponse validatedUser) {
        return rememberRepository.findAllByUid(validatedUser.getData().getUserId()).orElseThrow(() -> RememberedCardNotFoundException.EXCEPTION);
    }

    public Card getCard(String id, ValidatedResponse validatedUser) {
        return cardRepository.findById(Long.valueOf(id)).orElseThrow(() -> CardNotFoundException.EXCEPTION);
    }

    public List<Card> getCardsWithUser(ValidatedResponse validatedUser) {
        return cardRepository.findAllByUid(validatedUser.getData().getUserId()).orElseThrow(() -> CardNotFoundException.EXCEPTION);
    }

    public void updateCard(MultipartFile cardImage, String id, ValidatedResponse validatedUser) {
        if (cardImage == null) throw ImageNotExistException.EXCEPTION;
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("multipart-file-image", cardImage.getResource());
        ImageUploadResponse imageUploadResponse = webClientUtil.imageUpload(builder);
        if (imageUploadResponse.getCode() / 100 != 2) ImageNotUploadException.EXCEPTION(imageUploadResponse.getCode(), imageUploadResponse.getMessage());
        Card card = cardRepository.findById(Long.valueOf(id)).orElseThrow(() -> CardNotFoundException.EXCEPTION);
        card.setImagePath(webClientUtil.getImageServerPath() + "/api/siss/storages/images/" + imageUploadResponse.getData().getId());
        cardRepository.save(card);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteRemember(String id, ValidatedResponse validatedUser) {
        rememberRepository.deleteById(Long.valueOf(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCard(String id, ValidatedResponse validatedUser) {
        cardRepository.deleteById(Long.valueOf(id));
    }
}
