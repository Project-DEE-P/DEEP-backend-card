package com.dragonest.deepbackendcard.domain.card.domain.repository;

import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageCardRepository extends JpaRepository<ImageCard, Long> {

    List<ImageCard> findAllByUid(String uid);
}
