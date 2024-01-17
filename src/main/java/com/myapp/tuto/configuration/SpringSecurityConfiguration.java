package com.myapp.tuto.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
 	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth ->
			auth.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("/user").hasRole("User")
				.requestMatchers("/administration").hasRole("ADMIN")
				.requestMatchers("/accueil").permitAll()
				.anyRequest().authenticated()
				)
			.formLogin(Customizer.withDefaults())
			.formLogin( formLogin ->  formLogin.defaultSuccessUrl("/profile"))
			.logout( logout -> logout.logoutSuccessUrl("/accueil"))
		;
 		return http.build();
 	}

 	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
