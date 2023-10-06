# Custom Logic for Authentication

Earlier: Fetched the userdetails from the database to perform authentication.

We will implement UserDetailsService interface in Spring Security and override
loadUserByUsername method.

- Inside loadUserByUsername() method
  - fetch the records from the custom table
  - convert those details to userDetail object
  - Spring would then perform authentication
  - the custom logic would be written here.

- EazyBankUserDetail class would implement UserDetailService

```java
@Service
public class EazyBankUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // custom logic
    }
}
```
- Now, Spring Framework will forward this incoming request to DaoAuthenticationProvider

  - DaoAuthenticationProvider will look for the implementation classes for UserDetailsService