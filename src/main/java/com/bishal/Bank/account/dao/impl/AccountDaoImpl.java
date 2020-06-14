package com.bishal.Bank.account.dao.impl;

import com.bishal.Bank.account.dao.IAccountDao;
import com.bishal.Bank.account.model.BankAccount;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends AbstractBaseDaoImpl<BankAccount> implements IAccountDao {
    @Override
    public BankAccount create(BankAccount bankAccount) {
        return create(bankAccount, BankAccount.class);
    }

    @Override
    public BankAccount findById(String accountNumber) {
        return findById(accountNumber, BankAccount.class);
    }

    @Override
    public void update(Query query, Update update) {
        update(query, update, BankAccount.class);
    }
}
