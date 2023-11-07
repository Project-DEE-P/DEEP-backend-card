package com.dragonest.deepbackendcard.domain.card.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {

    TEMPLATE("TEMPLATE"), IMAGE("IMAGE");
    private final String value;
}