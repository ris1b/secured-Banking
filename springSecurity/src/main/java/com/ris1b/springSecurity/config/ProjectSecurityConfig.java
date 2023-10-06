package com.ris1b.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

    /* Created custom SecurityFilterChain Bean*/
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").authenticated()
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

    /** Created new Bean of JdbcUserDetailsManager, replacing InMemoryUserDetailsManager
        - userDetailsService() method takes in DataSource object
        When we add mysql dependency in the classpath and define it in application.properties
        Spring Boot will automatically create an object of DataSource inside the WebApplication

        - so when we pass DataSource object inside userDetailsService(), we are telling
        JdbcUserDetailsManager that there is a database that we have created and the details are
        present inside the DataSource
        so, while creating the object of JdbcUserDetailsManager, we need to make sure
    */
    /** Commenting this implementation of UserDetailsService in order to use
     * custom implementation of UserDetailsService by EazyBankUserDetails.
     * */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

}