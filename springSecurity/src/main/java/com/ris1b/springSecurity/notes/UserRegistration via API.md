### How to allow end-users to register via REST APIs

Two options to do so:
1. Implementing UserDetailsManager and override it's method

   - But we don't want to involve Spring security for creating, updating an user..
   - We need Spring security during the Authentication process, so we can simply use 
    ``loadUserByUsername()`` present in the UserDetailsService. 
   - We don't want to implement security for all the User related operations. As we can
   achieve the same by exposing Rest Services to the end user.
2. Exposing Rest Services to the end user

    - the end user can register themselves
    - created `LoginController`
    - LoginController takes credentials from the end user and save to the database table
    - and with same credentials, end user can login again.