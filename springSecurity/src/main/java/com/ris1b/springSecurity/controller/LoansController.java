package com.ris1b.springSecurity.controller;

import com.ris1b.springSecurity.model.Loans;
import com.ris1b.springSecurity.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {
    @Autowired
    private LoanRepository loanRepository;
    @GetMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestParam int id){
        List<Loans> loansList = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(loansList != null) {
            return loansList;
        }
//        return "Here are the Loans details";
        return null;
    }
}
