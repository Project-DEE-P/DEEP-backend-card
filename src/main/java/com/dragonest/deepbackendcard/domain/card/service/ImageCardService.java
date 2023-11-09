package com.dragonest.deepbackendcard.domain.card.service;

import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import com.dragonest.deepbackendcard.domain.card.domain.repository.ImageCardRepository;
import com.dragonest.deepbackendcard.domain.card.exception.CardNotCreateException;
import com.dragonest.deepbackendcard.domain.card.exception.ImageCardNotFoundException;
import com.dragonest.deepbackendcard.domain.card.presentation.dto.request.ImageCardRequest;
import com.dragonest.deepbackendcard.global.infra.RestRequest;
import com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data.ValidateData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageCardService {

    private final ImageCardRepository imageCardRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

//    @Value(value = "${webClient.servers.imageServer.path}")
//    private String imageServerPath;
//
//    @Value(value = "${webClient.servers.imageServer.key}")
//    private String imageServerKey;

    public ValidateData validate(String token) {
        return webClient.validate(userServerPath + "/v1/api/auth/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long create(ImageCardRequest request, String token) {
        ValidateData user = validate(token);
        List<ImageCard> imageCards = imageCardRepository.findAllByUid(user.getUserId());
        if (imageCards.size() >= 10) {
            throw CardNotCreateException.EXCEPTION;
        }
        return imageCardRepository.save(request.toEntity(user.getUserId())).getId();
    }

    public List<ImageCard> getCards(String token) {
        ValidateData user = validate(token);
        return imageCardRepository.findAllByUid(user.getUserId());
    }

    public ImageCard getCard(String id) {
        return imageCardRepository.findById(Long.valueOf(id)).orElseThrow(() -> ImageCardNotFoundException.EXCEPTION);
    }

    public void update(ImageCardRequest request, String id, String token) {
        ValidateData user = validate(token);
        ImageCard imageCard = imageCardRepository.findById(Long.valueOf(id)).orElseThrow(() -> ImageCardNotFoundException.EXCEPTION);
        imageCard.update(request.getName(), request.getCompany(), request.getAddress(), request.getPosition(), request.getMobile(), request.getTel(), request.getEmail(), request.getFax(), request.getHomepage(), request.getDepartment(), request.getImage());
        imageCardRepository.save(imageCard);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id, String token) {
        ValidateData user = validate(token);
        imageCardRepository.deleteById(Long.valueOf(id));
    }
}
