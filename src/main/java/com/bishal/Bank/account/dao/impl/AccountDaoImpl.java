package com.bishal.Bank.account.dao.impl;

import com.bishal.Bank.account.dao.IAccountDao;
import com.bishal.Bank.account.model.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends AbstractBaseDaoImpl<BankAccount> implements IAccountDao {
    @Override
    public BankAccount create(BankAccount bankAccount) {
        return create(bankAccount, BankAccount.class);
    }
}
