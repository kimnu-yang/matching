package com.matching.storage.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.matching")
@EnableJpaRepositories(basePackages = "com.matching")
@EnableJpaAuditing
public class JpaConfiguration {
}
