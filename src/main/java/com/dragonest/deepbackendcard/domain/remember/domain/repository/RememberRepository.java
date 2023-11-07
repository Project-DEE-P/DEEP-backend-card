package com.dragonest.deepbackendcard.domain.remember.domain.repository;

import com.dragonest.deepbackendcard.domain.card.enums.CardType;
import com.dragonest.deepbackendcard.domain.remember.domain.Remember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RememberRepository extends JpaRepository<Remember, Long> {

    Optional<List<Remember>> findAllByUserAndCardType(String user, CardType cardType);
    Optional<Remember> findAllByUserAndCardTypeAndCard(String user, CardType cardType, Long card);
}
