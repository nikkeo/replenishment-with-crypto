package org.example.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(auf -> {
                    auf.requestMatchers("/auth/**").permitAll();
                    auf.requestMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/house/**").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/street/save").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/street/getAll").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment").hasAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/getById").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN");
                    auf.requestMatchers("/apartment/getAll").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/save").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/deleteBy").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/update").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/getByVid").hasAnyAuthority("ROLE_ADMIN");
                    auf.requestMatchers("/apartment/getByName").hasAnyAuthority("ROLE_ADMIN");
                    auf.anyRequest().authenticated();
                })
                .formLogin()
                .and()
                .httpBasic();


        return http.build();
    }
}