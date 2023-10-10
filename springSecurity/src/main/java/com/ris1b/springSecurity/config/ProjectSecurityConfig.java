package com.ris1b.springSecurity.config;

import com.ris1b.springSecurity.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;


@Configuration
public class ProjectSecurityConfig {

    /* Created custom SecurityFilterChain Bean*/
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // creating object of csrf token request attribute
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .securityContext((context) -> context
                        /* made explicitSave false, to give the responsibility of generating JSESSIONID and storing Authentication details into securityContext to Spring Framework */
                        .requireExplicitSave(false))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)) // always create JSESSIONID after initial login

                .csrf((csrf) -> csrf
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        /* backend app has to send cookie and header value after the initial login,
                         so send token value as part of response to the UI app
                         We need a Filter class, CsrfCookieFilter */
                )
                /* Execute CsrfCookieFilter after BasicAuthenticationFilter.
                    After the BasicAuthenticationFilter, login will be completed
                    and then CsrfToken will be generated. The CsrfToke value will
                    be persisted in the response via CsrfCookieFilter
                 */
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
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