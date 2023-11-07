package com.dragonest.deepbackendcard.global.lib.webClient.dto.response.data;

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
