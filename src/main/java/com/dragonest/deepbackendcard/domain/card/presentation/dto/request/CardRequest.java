package com.dragonest.deepbackendcard.domain.card.presentation.dto.request;

import com.dragonest.deepbackendcard.domain.card.domain.Card;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.dragonest.deepbackendcard.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardRequest {

    @NotBlank(message = TEMPLATE_NOT_BLANK)
    private String template;

    @NotBlank(message = NAME_NOT_BLANK)
    private String name;

    @NotBlank(message = POSITION_NOT_BLANK)
    private String position;

    @NotBlank(message = DEPARTMENT_NOT_BLANK)
    private String department;

    @NotBlank(message = PHONE_NOT_BLANK)
    private String phone;

    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

    private String github;

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
                .build();
    }
}