package com.example.hamo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder getPasswordEncodeing() {
		return new BCryptPasswordEncoder();
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 00f091061b9b4a441d7267fae366aa68c70a08ba
