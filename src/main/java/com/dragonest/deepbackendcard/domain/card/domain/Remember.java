package com.dragonest.deepbackendcard.domain.card.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "remember")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Remember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Card card;
}
