package com.dragonest.deepbackendcard.domain.card.domain.repository;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<List<Card>> findAllByUid(String uid);
}
