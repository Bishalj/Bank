package com.bishal.Bank.customer.dao.impl;

import com.bishal.Bank.account.dao.impl.AbstractBaseDaoImpl;
import com.bishal.Bank.customer.dao.ICustomerDao;
import com.bishal.Bank.customer.model.Customer;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bishal.Bank.account.constant.AccountConstant.DbFieldMappers.BANK_ACCOUNT_NUMBERS;
import static com.bishal.Bank.account.constant.AccountConstant.DbFieldMappers._ID;

@Repository
public class CustomerDaoImpl extends AbstractBaseDaoImpl<Customer> implements ICustomerDao {
    @Override
    public void add(String panNumber, String accountNo) {
        Customer customer = findById(panNumber, Customer.class);
        if(customer == null){
            Customer newCustomerDetails = new Customer(panNumber, accountNo);
            create(newCustomerDetails, Customer.class);
        }else {
            updateAccountNumbers(panNumber, accountNo, customer);
        }
    }

    private void updateAccountNumbers(String panNumber, String accountNo, Customer customer) {
        List<String> accountNumbers = customer.getBankAccountNumbers();
        accountNumbers.add(accountNo);
        Query query = getUpdateCustomerDetailsQuery(panNumber);
        Update update = getUpdateAccountList(customer, accountNumbers);
        update(query, update, Customer.class);
    }

    private Update getUpdateAccountList(Customer customer, List<String> accountNumbers) {
        Update update = new Update()
                .set(BANK_ACCOUNT_NUMBERS, accountNumbers);
        return update;
    }

    private Query getUpdateCustomerDetailsQuery(String panNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where(_ID).is(panNumber));
        return query;
    }
}
