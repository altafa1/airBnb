package com.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity http
// )throws Exception{
//     http.csrf().disable().cors().disable();
//     http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
////     http.authorizeHttpRequests().requestMatchers("/api/v1/authorization/createuser","/api/v1/authorization/login","/api/v1/authorization/createpropertyowner")
////             .permitAll()
////             .requestMatchers("/api/v1/authorization/createpropertymanager").hasRole("ADMIN")
////             .requestMatchers("/api/v1/authorization/addproperty").hasAnyRole("MANAGER","OWNER","ADMIN")
////             .anyRequest().authenticated();
//        http.authorizeHttpRequests().anyRequest().permitAll();
//     return http.build();
// }


}
