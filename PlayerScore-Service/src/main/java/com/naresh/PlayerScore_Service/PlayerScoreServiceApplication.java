package com.naresh.PlayerScore_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PlayerScoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerScoreServiceApplication.class, args);
	}

}
