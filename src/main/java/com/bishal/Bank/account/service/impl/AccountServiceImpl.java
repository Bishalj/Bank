package com.bishal.Bank.account.service.impl;

import com.bishal.Bank.account.dao.IAccountDao;
import com.bishal.Bank.account.mapper.BankAccountMapper;
import com.bishal.Bank.account.model.BankAccount;
import com.bishal.Bank.account.service.IAccountService;
import com.bishal.Bank.common.enums.AccountType;
import com.bishal.Bank.customer.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.bishal.Bank.account.constant.AccountConstant.DbFieldMappers.*;

@Service
public class AccountServiceImpl implements IAccountService {

    private final static Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private IAccountDao accounDao;

    @Autowired
    private ICustomerService customerService;

    @Override
    public BankAccount createBankAccount(BankAccountMapper bankAccountMapper) {
        if(isAccountDetailsInvalid(bankAccountMapper))
            throw new IllegalArgumentException("Invalid request data");

        final BankAccount bankAccount = getBankAccountDetails(bankAccountMapper);
        LOG.info("account object: " + bankAccount);
        BankAccount accountDetails = accounDao.create(bankAccount);
        LOG.info("account details: " + accountDetails);
        customerService.addDetails(accountDetails.getPanNumber(), accountDetails.getAccountNo());
        return accountDetails;
    }

    @Override
    public BankAccount getBankDetails(String accountNumber) {
        return accounDao.findById(accountNumber);
    }

    @Override
    public void updateCurrentBalance(double remainingAmount, String accountNumber) {
        Query query = getFindByIdQuery(accountNumber);
        Update update = getUpdateAccountList(CURRENT_BALANCE, remainingAmount);
        accounDao.update(query, update);
    }

    private Query getFindByIdQuery(String accountNumber) {
        return getQuery(_ID, accountNumber);
    }

    private Query getQuery(String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(value));
        return query;
    }

    private Update getUpdateAccountList(String key, Double value) {
        Update update = new Update()
                .set(key, value);
        return update;
    }

//    private Query getUpdateCustomerDetailsQuery(String panNumber) {
//        return getQuery(_ID, panNumber);
//    }

    private BankAccount getBankAccountDetails(BankAccountMapper bankAccountMapper) {
        final BankAccount bankAccount = new BankAccount();
        //todo verify branch details in DB
        bankAccount.setBranchName(bankAccountMapper.getBranchName());
        bankAccount.setCurrentBalance(bankAccountMapper.getAmount());
        bankAccount.setPanNumber(bankAccountMapper.getPanNumber());
        mapOtherDetails(bankAccountMapper, bankAccount);
        return bankAccount;
    }

    private void mapOtherDetails(BankAccountMapper bankAccountMapper, BankAccount bankAccount) {
        Optional<AccountType> accountType = AccountType.getAccountType(bankAccountMapper.getAccountType());
        if(accountType.isPresent()){
            bankAccount.setAccountType(accountType.get().getType());
            bankAccount.setInterestRates(accountType.get().getInterestRates());
            bankAccount.setMinimumBalance(accountType.get().getMinimumBalance());
        }
    }

    private boolean isAccountDetailsInvalid(BankAccountMapper bankAccountMapper) {
        return ObjectUtils.isEmpty(bankAccountMapper) ||
                StringUtils.isEmpty(bankAccountMapper.getAccountType()) ||
                StringUtils.isEmpty(bankAccountMapper.getPanNumber()) ||
                StringUtils.isEmpty(bankAccountMapper.getBranchName()) ||
                bankAccountMapper.getAmount() == null;
    }
}
