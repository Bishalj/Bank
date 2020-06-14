package com.bishal.Bank.transaction.service.impl;

import com.bishal.Bank.account.model.BankAccount;
import com.bishal.Bank.account.service.IAccountService;
import com.bishal.Bank.transaction.dao.ITransactionDao;
import com.bishal.Bank.transaction.exception.InsufficientAmountException;
import com.bishal.Bank.transaction.mapper.TransactionMapper;
import com.bishal.Bank.transaction.model.Transaction;
import com.bishal.Bank.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.security.auth.login.AccountNotFoundException;

import java.util.List;

import static com.bishal.Bank.common.enums.TransactionType.DEPOSIT;
import static com.bishal.Bank.common.enums.TransactionType.WITHDRAW;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionDao transactionDao;

    @Autowired
    private IAccountService accountService;

    @Override
    public Transaction transactionAmount(TransactionMapper transactionMapper, String type) throws AccountNotFoundException, InsufficientAmountException {
        if(isRequestDataInvalid(transactionMapper))
            throw new IllegalArgumentException("Invalid transaction data");

        //should have used transaction rollback commit log
        //should have use factory patter for handling withdrawl and deposit
        final BankAccount bankAccount = getAccountDetails(transactionMapper.getAccountNumber());
        if(bankAccount == null)
            throw new AccountNotFoundException("Account number do not exist");
        else if(isTypeWithdraw(type) && isRemainingBalanceInvalid(bankAccount, transactionMapper))
            throw new InsufficientAmountException("Balance is Insufficient");

        double remainingAmount = getAmountAfterTransaction(bankAccount, transactionMapper, type);
        Transaction transaction = new Transaction(transactionMapper.getAmount(), bankAccount.getAccountType(), bankAccount.getAccountNo());
        Transaction transactionDetails = transactionDao.insert(transaction);
        accountService.updateCurrentBalance(remainingAmount, transactionMapper.getAccountNumber());
        return transactionDetails;
    }

    @Override
    public List<Transaction> getMiniStatement(String accountNumber) {
        return transactionDao.getStatements(accountNumber);
    }

    private boolean isTypeWithdraw(String type) {
        return WITHDRAW.name().equalsIgnoreCase(type);
    }

    private boolean isTypeDeposit(String type) {
        return DEPOSIT.name().equalsIgnoreCase(type);
    }


    private boolean isRemainingBalanceInvalid(BankAccount bankAccount, TransactionMapper transactionMapper) {
        return bankAccount.getCurrentBalance() - bankAccount.getMinimumBalance() - transactionMapper.getAmount() < 0;
    }

    private double getAmountAfterTransaction(BankAccount bankAccount, TransactionMapper transactionMapper, String type) {
        if(isTypeWithdraw(type))
            return bankAccount.getCurrentBalance() - transactionMapper.getAmount();
        else if(isTypeDeposit(type))
            return bankAccount.getCurrentBalance() + transactionMapper.getAmount();

        throw new IllegalArgumentException("Invalid type of transaction");
    }

    private BankAccount getAccountDetails(String accountNumber) {
        return accountService.getBankDetails(accountNumber);
    }

    private boolean isRequestDataInvalid(TransactionMapper transactionMapper) {
        return ObjectUtils.isEmpty(transactionMapper) ||
                StringUtils.isEmpty(transactionMapper.getAccountNumber()) ||
                transactionMapper.getAmount() == null;
    }
}
