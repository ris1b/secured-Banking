#

### Agenda
1. How to configure multiple users inside the application memory
2. How to create and store the user credentials in DB

#### Using InMemoryUserDetailsManager
Instead of giving user definition in application.properties, we can define
multiple users along with their authorities using InMemoryUserDetailsManager.

- We will create InMemoryUserDetailsManager Bean

- InMemoryUserDetailsManager extends UserDetailsManager