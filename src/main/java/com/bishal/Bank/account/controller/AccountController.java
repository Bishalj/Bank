package com.bishal.Bank.account.controller;

import com.bishal.Bank.account.mapper.BankAccountMapper;
import com.bishal.Bank.account.model.BankAccount;
import com.bishal.Bank.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity createBankAccount(@RequestBody BankAccountMapper bankAccountMapper){
        try{
            final BankAccount bankAccount = accountService.createBankAccount(bankAccountMapper);
            return ResponseEntity.ok("Account created Successfully, Account Number: " + bankAccount.getAccountNo());
        }catch (Exception ex){
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }
}
