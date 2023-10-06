# User Details Manager 

## Implementation classes provided by Spring Framefork:
1. InMemoryUserDetailsManager
2. JdbcUserDetailsManager
3. LdapUserDetailsManager


### In Memory User Details Manager

- ``createUser(UserDetails user)`` method gets invoked for all the UserDetails passed to 
the constructor of InMemoryUserDetailsManager.
  - it populates the user details into a map
- Similar logic for ``deleteUser(UserDetails user)`` and ``updateUser(UserDetails user)`` methods
- ``loadUserByUsername(String username)`` this method is responsible to load the user details from the storage system
i.e. In-Memory here.
  - These details would be passed to the provider, and provider would do the check of comparing passwords
  and would create a successful Authentication object.
- Note: 
  - Based on the Beans defined in the project configurations, so the same managers would be 
    used by the spring framework (by invoking them via one of the authentication providers).
  - By default, DaoAuthenticationProvider is used.
