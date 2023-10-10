# implementation of CSRF Tokens in backend

Inside Spring Security configurations, create an object of `CsrfTokenRequestAttributeHandler`
- it is an implementation of CsrfTokenRequestHandler.
- it makes the CsrfToken available as a request attribut
- and resolving the token value as a header or a parameter value.

```java
public class CsrfTokenRequestAttributeHandler implements CsrfTokenRequestHandler {
//    ......
}
```

We will create an object of this attribute handler

....


```
csrf((csrf) -> csrf
.csrfTokenRequestHandler(requestHandler)
.ignoringRequestMatchers("/contact", "/register")
.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
```
#### Above why withHttpOnlyFalse() ?


