# Servlets & Filters

Any java web application accepts the request and send the response via HTTP protocol.
Using the HTTP Protocols, the browsers can send a request to the backend web application.

Servlet container or Web Servers sits between the Java App and the browser.
Eg. Apache Tomcat is a WebServer.

These containers convert the HTTP Message from the Browser into an HTTP Servlet Request Object,and this Request object will be 
passed to the Spring Framework.

While sending the Response back to the Browser, the Servlet container takes the object of the 
Http Servlet Response and again converts back it to the HTTP Servlet message.

Similarly, we have Filters inside the Web Application. Filters are special kind of servlets which 
we can use to intercept each and every request that is coming to our web application. 

### Spring Security Internal Flow
[Internal flow](https://github.com/ris1b/SpringSecurity/blob/main/SpringSecurityNotes.md)

### What logic is required to protect all the URL's by default ?
- By default, Spring Security Framework protects all the paths present inside the web application.
    This reason is due to the defaultSecurityFilterChain() implementation of SpringBootWebSecurityConfiguration class: 
``` java
// This method returns SecurityFilterChain Bean.
@Bean
@Order(SecurityProperties.BASIC_AUTH_ORDER)
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
            // any incoming request should be authenticated 
            http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
			http.formLogin(withDefaults());
			http.httpBasic(withDefaults());
			return http.build();
		}
```
### [How to define our own custom Security requirements?](Implementing-Custom-Security.md)

[//]: # ([How to define our own custom Security requirements?]&#40;url&#41;)

