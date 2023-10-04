package com.dragonest.deepbackendcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DeepBackendCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeepBackendCardApplication.class, args);
    }

}
