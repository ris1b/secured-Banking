package com.ris1b.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    /*
    * Created custom SecurityFilterChain Bean.
    * */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
       /* Basic Syntax
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
        */

        /* Code1
            Authorizing requests using Lambda (used in updated Udemy Course)
            Here, authenticating few API's and permitting other API's
        http
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").authenticated()
                        .requestMatchers("/contact", "/notices").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
         */


        /* Code2
            The below code works fine. Using this code currently.
            This code is inspired from Spring Docs
            When both form login and HTTP Basic authentication are configured,
            users can choose which authentication method they want to use.
            For example, users could use form login to authenticate from a web browser
            and HTTP Basic authentication to authenticate from a mobile app.
         */
        http
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").authenticated()
                        .requestMatchers("/contact", "/notices").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());


        /* Code3
            This below code works fine. Added defaultSuccessUrl("/login")

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").authenticated()
                        .requestMatchers("/contact", "/notices").permitAll()
                )
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/login")        // Added
                        // .loginPage("/login")             // Commented
                        // .permitAll()                     // Commented
                )
                .httpBasic(httpBasicConfigurer -> httpBasicConfigurer
                        .realmName("My Realm")
                );
                // .rememberMe(Customizer.withDefaults());  // Commented
         */

        /* This example is from Spring security Docs of how to use Lamda DSL
        https://docs.spring.io/spring-security/reference/migration-7/configuration.html

        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .httpBasic(httpBasicConfigurer -> httpBasicConfigurer
                        .realmName("My Realm")
                )
                .rememberMe(Customizer.withDefaults());
         */
        return http.build();
    }
}

/* Code2
        This code is inspired from Spring Docs
            When both form login and HTTP Basic authentication are configured,
            users can choose which authentication method they want to use.
            For example, users could use form login to authenticate from a web browser
            and HTTP Basic authentication to authenticate from a mobile app.
        http
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/myAccount", "myBalance", "/myCards", "/myLoans").authenticated()
                        .requestMatchers("/contact", "/notices").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        */
