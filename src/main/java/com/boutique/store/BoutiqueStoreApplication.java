package com.boutique.store;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * This is the Spring Boot Application class.  This is where we make sure we're NOT running in Headless mode and that
 * the WebApplicationType is set to NONE.
 */
@SpringBootApplication
@EnableJpaAuditing
public class BoutiqueStoreApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(BoutiqueStoreApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
