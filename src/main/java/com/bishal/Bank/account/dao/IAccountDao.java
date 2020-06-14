package com.bishal.Bank.account.dao;

import com.bishal.Bank.account.model.BankAccount;
import com.bishal.Bank.customer.model.Customer;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface IAccountDao {

    BankAccount create(BankAccount bankAccount);

    BankAccount findById(String accountNumber);

    void update(Query query, Update update);
}
