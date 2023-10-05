package com.ris1b.springSecurity.repository;

import com.ris1b.springSecurity.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    /*
    * Spring Data JPA runs a select query by having email inside WHERE condition.
    * This is Derived method name query. The query that is going to be executed inside Database
    * server will be derived based upon the method name inside this repository.
     */
    List<Customer> findByEmail(String email);

}
