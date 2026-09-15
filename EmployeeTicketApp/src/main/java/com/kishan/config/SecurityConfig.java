package com.kishan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .requestMatchers("/tickets/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {

                    if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {

                        response.sendRedirect("/admin/dashboard");

                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {

                        response.sendRedirect("/employee/dashboard");
                    }
                })
                .permitAll()
            )

            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}