package br.com.fatec.ChopperHouseGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@SpringBootApplication
public class ChopperHouseGamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChopperHouseGamesApplication.class, args);
	}

}
