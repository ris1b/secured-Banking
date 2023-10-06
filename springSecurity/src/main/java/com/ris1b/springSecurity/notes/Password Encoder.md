# How Passwords are validated ?

Aim: the role of Password encoders during the Authentication of end-user.

1. Default Logic without Password encoders

   - Default PasswordEncoders handles the password in Plain Text format
   - User enters username and password
   - Log's in
   - Spring Security Framework will execute the login in Authentication Provider, and loads detail
   from storage system via `loadUserByUsername()` method of UserDetailsManager.
   - Once User details are loaded, Spring Framework compare the password provided by 
   end user with the passwords in the database.
   - This comparison will be a PLAINTEXT comparison.
   - If password matches, successful login !
   
        #### Important Classes and Interfaces:

        - DaoAuthenticationProvider `extends` AbstractUserDetailsAuthenticationProvider
        - AbstractUserDetailsAuthenticationProvider
          `implements` AuthenticationProvider
        - AuthenticationProvider has `authenticate()` and `supports()`
      

2. PasswordEncoder (using hashing)
   
   - has two abstract methods `encode()`, `matches()`, and a default method `upgradeEncoding()`
   - `encode()`
     - used during registration process of user
     - this method converts the plaintext password into an encrypted value
     based upon the PasswordEncoder used
   
   - `matches()`
     - this method is used during the login to compare the passwords

   - `updgradeEncoding()`
     - The idea is to encode the password two times ie. doing the hashing twice.
     - returns true if encoded password should be encoded again for better security
     - else returns false by default.

### Different PasswordEncoder implementations
1. NoOpPasswordEncoder
2. StandardPasswordEncoder
3. BCryptPasswordEncoder
4. SCryptPasswordEncoder
5. Argon2PasswordEncoder
6. Pdkdf2PasswordEncoder

