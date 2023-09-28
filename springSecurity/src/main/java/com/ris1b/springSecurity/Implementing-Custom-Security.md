# Implementing custom Security requirements

SpringBootWebSecurityConfiguration class secures servlet applications.

* The default configuration for web security. It relies on Spring Security's content-negotiation strategy to determine what sort of authentication to use.
* If the user specifies their own SecurityFilterChain bean, this will back-off 
completely and the users should specify all the bits that they want to configure as part of the custom security configuration.

Standard implementation of SecurityFilterChain.
``` java
@Bean
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception { 
            http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
			http.formLogin(withDefaults());
			http.httpBasic(withDefaults());
			return http.build();
		}
```
- any incoming request coming to the spring boot application should be authenticated
- http.formLogin(withDefaults());
    - the request can come via html form, such request should be authenticated
- http.httpBasic(withDefaults()); 
    
    - whereas if the request is coming from postman or rest-api invocation, in such scenarios we are going to send the request along with the user credentials by following the http basic standards
- The framework accordingly tries to authenticate the request.

### Now we can define the bean of SecurityFilterChain inside the configuration class.

[//]: # (try giving the url of the configuration Class)
[Custom Configuration link](url of the package)

When we want to define our own custom requirements we would invoke ``requestmatchers() method`` where we would define our API Paths.


