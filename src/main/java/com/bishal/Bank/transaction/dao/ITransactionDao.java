package com.bishal.Bank.transaction.dao;


import com.bishal.Bank.transaction.model.Transaction;

import java.util.List;

public interface ITransactionDao {
    Transaction insert(Transaction transaction);

    List<Transaction> getStatements(String accountNumber);
}
