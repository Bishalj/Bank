package com.bishal.Bank.transaction.controller;

import com.bishal.Bank.transaction.model.Transaction;
import com.bishal.Bank.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction-history")
public class TransactionHistoryAPI {

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/mini-statement/{accountNumber}")
    public ResponseEntity getMiniStatement(@PathVariable("accountNumber") String accountNumber){
        try{
            List<Transaction> transactions = transactionService.getMiniStatement(accountNumber);
            return ResponseEntity.ok(transactions);
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body(ex.getMessage());
        }
    }
}
