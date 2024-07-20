package com.pekilla.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final UserDetailsService userDetailsService;

    private final String[] WHITE_LIST_URL = {
        "/api/auth/**",
        "/api/categories/**",
        "/api/posts/**",
        "/api/users/exists/**"
    };

    private final String[] AUTH_LIST = {
        "/api/setting/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                authCustomizer -> authCustomizer
                    .requestMatchers(WHITE_LIST_URL).permitAll()
                    .requestMatchers(AUTH_LIST).authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/categories").authenticated()
                    .requestMatchers(HttpMethod.PATCH, "/api/categories").authenticated()
                    .requestMatchers("/api/categories/edit").authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .userDetailsService(userDetailsService)
            .cors(c -> c.configurationSource(corsConfigurationSource))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
