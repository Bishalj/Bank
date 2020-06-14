package com.bishal.Bank.account.service;

import com.bishal.Bank.account.mapper.BankAccountMapper;
import com.bishal.Bank.account.model.BankAccount;

public interface IAccountService {

    BankAccount createBankAccount(BankAccountMapper bankAccountMapper);

    BankAccount getBankDetails(String accountNumber);

    void updateCurrentBalance(double remainingAmount, String accountNumber);
}
