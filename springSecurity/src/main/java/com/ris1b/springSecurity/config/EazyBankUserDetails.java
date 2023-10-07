package com.ris1b.springSecurity.config;

import com.ris1b.springSecurity.model.Customer;
import com.ris1b.springSecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation of UserDetails, and since we don't want to
 * use the DaoAuthenticationProvider and instead use our own Authentication logic,
 * that is, EazyBankUsernamePwdAuthenticationProvider.
 * So this class should be deleted.
 * */

//@Service
//public class EazyBankUserDetails implements UserDetailsService {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String userName;
//        String password;
//        List<GrantedAuthority> authorities;
//        List<Customer> customer = customerRepository.findByEmail(username);
//
//        if(customer.size() == 0){
//            throw new UsernameNotFoundException("User details not found for the user: "+ username);
//        } else{
//            userName = customer.get(0).getEmail();
//            password = customer.get(0).getPwd();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
//        }
//        /* now sending the User object to the Spring Security Framework where
//        * the authentication will happen.
//        * Inside DaoAuthenticationProvider, passwords will be compared from the
//        * database and from the request*/
//        return new User(userName, password, authorities);
//    }
//
//}
