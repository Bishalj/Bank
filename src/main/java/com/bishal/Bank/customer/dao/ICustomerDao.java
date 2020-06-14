package com.bishal.Bank.customer.dao;

import com.bishal.Bank.customer.model.Customer;

public interface ICustomerDao {
    void add(String panNumber, String accountNo);
}
