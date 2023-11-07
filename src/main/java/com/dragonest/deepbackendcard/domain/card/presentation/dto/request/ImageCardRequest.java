package com.dragonest.deepbackendcard.domain.card.presentation.dto.request;

import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.dragonest.deepbackendcard.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageCardRequest {

    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = COMPANY_NOT_BLANK)
    private String company;

    @NotBlank(message = ADDRESS_NOT_BLANK)
    private String address;

    @NotBlank(message = POSITION_NOT_BLANK)
    private String position;

    @NotBlank(message = MOBILE_NOT_BLANK)
    private String mobile;

    @NotBlank(message = TELL_NOT_BLANK)
    private String tel;

    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = FAX_NOT_BLANK)
    private String fax;

    @NotBlank(message = HOMEPAGE_NOT_BLANK)
    private String homepage;

    @NotBlank(message = DEPARTMENT_NOT_BLANK)
    private String department;

    public ImageCard toEntity(String uid, String image) {
        return ImageCard.builder()
                .uid(uid)
                .name(name)
                .company(company)
                .address(address)
                .position(position)
                .mobile(mobile)
                .tel(tel)
                .email(email)
                .fax(fax)
                .homepage(homepage)
                .department(department)
                .image(image)
                .build();
    }
}