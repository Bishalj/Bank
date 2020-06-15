package com.bishal.Bank.transaction.controller;

import com.bishal.Bank.transaction.exception.InsufficientAmountException;
import com.bishal.Bank.transaction.mapper.TransactionMapper;
import com.bishal.Bank.transaction.model.Transaction;
import com.bishal.Bank.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionAPI {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/{type}")
    public ResponseEntity transaction(@RequestBody TransactionMapper transactionMapper, @PathVariable("type") String type){
        try{
            Transaction transaction = transactionService.transactionAmount(transactionMapper, type);
            return ResponseEntity.ok("Transaction Successfully with transaction Id: "+ transaction.getTransactionId());
        } catch (AccountNotFoundException | InsufficientAmountException ex) {
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }
}
