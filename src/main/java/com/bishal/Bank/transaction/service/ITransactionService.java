package com.bishal.Bank.transaction.service;

import com.bishal.Bank.transaction.exception.InsufficientAmountException;
import com.bishal.Bank.transaction.mapper.TransactionMapper;
import com.bishal.Bank.transaction.model.Transaction;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface ITransactionService {

    Transaction transactionAmount(TransactionMapper transactionMapper, String type) throws AccountNotFoundException, InsufficientAmountException;

    List<Transaction> getMiniStatement(String accountNumber);
}
