package com.tiagorafaell.cifrascatolicas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // libera acesso ao H2
                        .requestMatchers("/publico").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .csrf(csrf -> csrf.disable()) // necessário pra H2 funcionar
                .headers(headers -> headers.frameOptions().disable()); // necessário pra H2 funcionar

        return http.build();
    }
}

