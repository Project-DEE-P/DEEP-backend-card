package com.dragonest.deepbackendcard.domain.card.presentation.dto.request;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.dragonest.deepbackendcard.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequest {

    @NotNull(message = TEMPLATE_NOT_NULL)
    private String template;

    @NotNull(message = NAME_NOT_NULL)
    private String name;

    @NotNull(message = POSITION_NOT_NULL)
    private String position;

    @NotNull(message = DEPARTMENT_NOT_NULL)
    private String department;

    @NotNull(message = PHONE_NOT_NULL)
    private String phone;

    @NotNull(message = EMAIL_NOT_NULL)
    private String email;

    private String github;

    @NotBlank(message = IMAGE_NOT_BLANK)
    private String image;

    public Card toEntity(String uid) {
        return Card.builder()
                .uid(uid)
                .template(template)
                .name(name)
                .position(position)
                .department(department)
                .phone(phone)
                .email(email)
                .github(github)
                .image(image)
                .build();
    }
}