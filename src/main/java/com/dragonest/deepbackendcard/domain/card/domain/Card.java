package com.dragonest.deepbackendcard.domain.card.domain;

import com.dragonest.deepbackendcard.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String template;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String github;

    @Column(nullable = false)
    private String image;

    public void update(String template, String name, String position, String department, String phone, String email, String github, String image) {
        this.template = template;
        this.name = name;
        this.position = position;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.github = github;
        this.image = image;
    }
}
