package com.bishal.Bank.customer.service.impl;

import com.bishal.Bank.customer.dao.ICustomerDao;
import com.bishal.Bank.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao customerDao;

    @Override
    public void addDetails(String panNumber, String accountNo) {
        //todo added rollback feature if some exception
        if(isCustomerDetailsInvalid(panNumber, accountNo))
            throw new IllegalArgumentException("Customer details are not present");

        customerDao.add(panNumber, accountNo);
    }

    private boolean isCustomerDetailsInvalid(String panNumber, String accountNo) {
        return StringUtils.isEmpty(panNumber) ||
                StringUtils.isEmpty(accountNo);
    }
}
