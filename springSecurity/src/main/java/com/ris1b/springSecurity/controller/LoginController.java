package com.ris1b.springSecurity.controller;

import com.ris1b.springSecurity.model.Customer;
import com.ris1b.springSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        System.out.println("*************** HERE *************");
        try{
            savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Successfully registered user");
            }

        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred " + e.getMessage());
        }

        return response;
    }

    @PostMapping("/checking")
    public String checking(@RequestBody Map<String,Object> customer){
        Map savedCustomer = null;
        ResponseEntity response = null;
        Customer saved = new Customer();
        saved.setEmail((String) customer.get("email"));
//        saved.setId();
        saved.setPwd((String) customer.get("pwd"));
        saved.setRole((String) customer.get("role"));

        Customer savedBoy = customerRepository.save(saved);

        return savedBoy.getEmail() + " saved 100";
    }

}
