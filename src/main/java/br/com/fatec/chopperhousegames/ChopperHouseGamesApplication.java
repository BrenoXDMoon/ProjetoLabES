package br.com.fatec.chopperhousegames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class ChopperHouseGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChopperHouseGamesApplication.class, args);
	}

}
