package com.dragonest.deepbackendcard.domain.card.domain.repository;

import com.dragonest.deepbackendcard.domain.card.domain.Remember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RememberRepository extends JpaRepository<Remember, Long> {

    Optional<List<Remember>> findAllByUid(String uid);
}
