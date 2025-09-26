package com.example.todolist.config;


import com.example.todolist.exceptions.CustomAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationHandler customAuthenticationHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize-> authorize
                                .requestMatchers("/api/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin-> formLogin
                        .loginProcessingUrl("/api/auth/login")
                        .permitAll()
                        .failureHandler(customAuthenticationHandler)
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }
}
