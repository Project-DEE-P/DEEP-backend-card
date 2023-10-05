package com.dragonest.deepbackendcard.global.lib.webClient.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateData {

    private String userId;
    private String userRole;
    private String userStatus;
}
