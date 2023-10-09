package com.ris1b.springSecurity.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;


@Configuration
public class ProjectSecurityConfig {

    /* Created custom SecurityFilterChain Bean*/
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfig = new CorsConfiguration();
                        corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsConfig.setAllowedMethods(Collections.singletonList("*"));
                        corsConfig.setAllowCredentials(true);
                        corsConfig.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfig.setMaxAge(3600L);

                        return corsConfig;
                    }
                }))
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans", "/user").authenticated()
                        .requestMatchers("/contact", "/notices", "/register").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /*Here, we are communicating to Spring Security that how Passwords are stored*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /* Created new Bean of JdbcUserDetailsManager, replacing InMemoryUserDetailsManager
        - userDetailsService() method takes in DataSource object
        When we add mysql dependency in the classpath and define it in application.properties
        Spring Boot will automatically create an object of DataSource inside the WebApplication

        - so when we pass DataSource object inside userDetailsService(), we are telling
        JdbcUserDetailsManager that there is a database that we have created and the details are
        present inside the DataSource
        so, while creating the object of JdbcUserDetailsManager, we need to make sure
    */
    /* Commenting this implementation of UserDetailsService in order to use
     * custom implementation of UserDetailsService by EazyBankUserDetails.
     * */
//    @Bean
//    public UserDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

}