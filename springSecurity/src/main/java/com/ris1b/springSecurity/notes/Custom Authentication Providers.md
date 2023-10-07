# Custom Authentication Providers

- The default AuthenticationProvider in Storing security is DaoAuthenticationProvider.

- Different Scenarios for authentication for end-users:
  - authenticating by using the username and password
  - accepting OAuth2 authentication
  - accept OTP authentication
- Above, there were different requirements for the same application based upon different 
  scenarios.
- So, there can be multiple Authentication Providers
- Flow:
  1. `Spring Security Filters` converts the user credentials
  into an Authentication object
  2. `ProviderManager` is responsible to invoke all the AuthenticationProviders inside the application

### Methods inside `AuthenticationProvider` interface

```java
public interface AuthenticationProvider {
	
    Authentication authenticate(Authentication authentication) throws AuthenticationException;	
	
    boolean supports(Class<?> authentication);

}
```
- We will override these methods to provide our own custom authentication provider.
- `authenticate()` method will have all the authentication logic
  - the authenticate object will have user credentials populated by Security Filters
  - we also need to populate the successful authentication info
  - we would return a authentication object which has successful authentication information, 
  based on which the `ProviderManager` decides to invoke other authentication providers or not.
- `supports()` method is used to communicate to spring security about what type of authentication
we want to support via AuthenticationProvider

### DaoAuthenticationProvider implementation of supports() method
```java
public abstract class AbstractUserDetailsAuthenticationProvider {
  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
```
- it handles all the authentication which are of type UsernamePasswordAuthenticationToken
- to authenticate username and passwords of users, we should use UsernamePasswordAuthenticationToken token


###### CustomAuthentication given in EazyBankUsernamePwdAuthenticationProvider

[//]: # (give link to that class later)