package com.sumfi.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class SpringConfig {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
