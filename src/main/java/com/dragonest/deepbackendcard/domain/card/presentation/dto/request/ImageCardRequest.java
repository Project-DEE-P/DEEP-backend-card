package com.dragonest.deepbackendcard.domain.card.presentation.dto.request;

import com.dragonest.deepbackendcard.domain.card.domain.ImageCard;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.dragonest.deepbackendcard.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageCardRequest {

    @NotNull(message = NAME_NOT_NULL)
    private String name;

    @NotNull(message = COMPANY_NOT_NULL)
    private String company;

    @NotNull(message = ADDRESS_NOT_NULL)
    private String address;

    @NotNull(message = POSITION_NOT_NULL)
    private String position;

    @NotNull(message = MOBILE_NOT_NULL)
    private String mobile;

    @NotNull(message = TELL_NOT_NULL)
    private String tel;

    @NotNull(message = EMAIL_NOT_NULL)
    private String email;

    @NotNull(message = FAX_NOT_NULL)
    private String fax;

    @NotNull(message = HOMEPAGE_NOT_NULL)
    private String homepage;

    @NotNull(message = DEPARTMENT_NOT_NULL)
    private String department;

    @NotBlank(message = IMAGE_NOT_BLANK)
    private String image;

    public ImageCard toEntity(String uid) {
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