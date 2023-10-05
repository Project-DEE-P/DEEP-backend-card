package com.dragonest.deepbackendcard.global.lib.webClient.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidatedUser {

    private String uid;
    private String userStatus;
    private String userRole;
}
