package com.ris1b.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


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

    /* Commenting InMemoryUserDetailsManager for using the Authentication via JDBC */
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//
//        /* Approach1
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .authorities("read")
//                .build();
//         */
//
//        /* Approach2
//         */
//        UserDetails admin = User.withUsername("admin")
//                .password("123")
//                .authorities("admin")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("123")
//                .authorities("read")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    /*
    * Created new Bean of JdbcUserDetailsManager, replacing InMemoryUserDetailsManager
        - userDetailsService() method takes in DataSource object
        When we add mysql dependency in the classpath and define it in application.properties
        Spring Boot will automatically create an object of DataSource inside the WebApplication

        - so when we pass DataSource object inside userDetailsService(), we are telling
        JdbcUserDetailsManager that there is a database that we have created and the details are
        present inside the DataSource
        so, while creating the object of JdbcUserDetailsManager, we need to make sure
    * */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    /*
    * Here, we are communication to Spring Security that how are passwords are stored*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

