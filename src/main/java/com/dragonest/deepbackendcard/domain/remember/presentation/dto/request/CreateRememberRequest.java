package com.dragonest.deepbackendcard.domain.remember.presentation.dto.request;

import com.dragonest.deepbackendcard.domain.card.enums.CardType;
import com.dragonest.deepbackendcard.domain.remember.domain.Remember;
import com.dragonest.deepbackendcard.global.annotation.EnumValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.dragonest.deepbackendcard.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRememberRequest {

    @NotNull(message = CARD_TYPE_NOT_NULL)
    @EnumValid(enumClass = CardType.class, message = CARD_TYPE_ENUM_VALUE)
    private CardType cardType;

    @NotBlank(message = CARD_ID_NOT_BLANK)
    private Long cardId;

    public Remember toEntity(String userId) {
        return Remember.builder()
                .user(userId)
                .cardType(cardType)
                .card(cardId)
                .build();
    }
}
