package com.dragonest.deepbackendcard.domain.card.domain;

import com.dragonest.deepbackendcard.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_card")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fax;

    @Column(nullable = false)
    private String homepage;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String image;

    public void update(String name, String company, String address, String position, String mobile, String tel, String email, String fax, String homepage, String department) {
        this.name = name;
        this.company = company;
        this.address = address;
        this.position = position;
        this.mobile = mobile;
        this.tel = tel;
        this.email = email;
        this.fax = fax;
        this.homepage = homepage;
        this.department = department;
    }
}
