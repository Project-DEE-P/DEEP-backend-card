package com.dragonest.deepbackendcard.global.util;

import com.dragonest.deepbackendcard.global.config.webClient.WebClientConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
public class WebClientUtil {

    private final WebClientConfig webClientConfig;

}
