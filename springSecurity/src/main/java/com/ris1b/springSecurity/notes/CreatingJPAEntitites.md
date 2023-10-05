# Creating JPA  Entity for customer table

- create entity class, to fetch the records from customer table using Spring
  Data JPA Framework.
- created a model package
- To fetch the records from customer table --> created a repository
- We can use the below annotations if we have created the repository outside the main package of our application:

  - mentioning package name where the repositories are present
    ``
      @EnableJpaRepositories("com.package.repositoy")
     ``
  - and ``
    @EntityScan("com.package.model")
    `` to scan the entities