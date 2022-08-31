package br.com.fatec.chopperhousegames.outbound.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "br.com.fatec.chopperhousegames.outbound.repository.jpa")
public class JpaConfiguration {
}
