package com.ris1b.springSecurity.controller;

import com.ris1b.springSecurity.model.Customer;
import com.ris1b.springSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private CustomerRepository customerRepository;

    /*
    * During registration process, we need to encode the password
    * and store in database*/
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        System.out.println("*************** HERE *************");
        try{
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
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

    @RequestMapping("/user")
    private Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());

        if(customers.size() > 0){
            return customers.get(0);
        } else {
            return null;
        }
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
