
package com.kishan.employeeexpensetracker.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kishan.employeeexpensetracker.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    // Password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    // CORS configuration for React frontend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:5173"));

        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        configuration.setAllowedHeaders(
                List.of("Authorization", "Content-Type"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // Spring Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disable CSRF for REST API
            .csrf(csrf -> csrf.disable())

            // Enable CORS
            .cors(cors -> {})

            // JWT-based stateless authentication
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))

            // Endpoint authorization
            .authorizeHttpRequests(auth -> auth

                // Public endpoints
                .requestMatchers(
                        "/api/users/register",
                        "/api/users/login"
                ).permitAll()

                // Manager-only endpoints
                .requestMatchers(
                        HttpMethod.GET,
                        "/api/users/employees"
                ).hasRole("MANAGER")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/users/change-manager/*"
                ).hasRole("MANAGER")

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/expenses/all"
                ).hasRole("MANAGER")

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/expenses/status"
                ).hasRole("MANAGER")

                .requestMatchers(
                        HttpMethod.PUT,
                        "/api/expenses/*/status"
                ).hasRole("MANAGER")

                // Employee-only endpoints
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/expenses/submit"
                ).hasRole("EMPLOYEE")

                .requestMatchers(
                        HttpMethod.GET,
                        "/api/expenses/my"
                ).hasRole("EMPLOYEE")

                // All other endpoints require authentication
                .anyRequest().authenticated()
            )

            // Add JWT filter
            .addFilterBefore(
                    jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
