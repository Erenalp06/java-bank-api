package com.teksen.bank_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailManager(){

        UserDetails erenalp = User.builder()
            .username("erenalp")
            .password("{noop}test123")
            .roles("ADMIN")
            .build();

            UserDetails mahmut = User.builder()
            .username("mahmut")
            .password("{noop}test123")
            .roles("ADMIN", "MODERATOR")
            .build();

            UserDetails selin = User.builder()
            .username("selin")
            .password("{noop}test123")
            .roles("ADMIN", "MODERATOR", "USER")
            .build();

            return new InMemoryUserDetailsManager(erenalp, mahmut, selin);
        
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
            .requestMatchers(HttpMethod.GET, "/api/v1/transactions/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/v1/transactions/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/v1/transactions/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/transactions/**").hasRole("ADMIN")
            .requestMatchers("/api/v1/banks/**").permitAll()
            .requestMatchers("/api/v1/users/**").permitAll()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
