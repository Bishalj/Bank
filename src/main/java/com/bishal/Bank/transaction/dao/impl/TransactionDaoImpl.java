package com.bishal.Bank.transaction.dao.impl;

import com.bishal.Bank.account.dao.impl.AbstractBaseDaoImpl;
import com.bishal.Bank.transaction.dao.ITransactionDao;
import com.bishal.Bank.transaction.model.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import static com.bishal.Bank.transaction.constant.TransactionConstant.ACCOUNT_NUMBER;
import static com.bishal.Bank.transaction.constant.TransactionConstant.CREATED_AT;

@Repository
public class TransactionDaoImpl extends AbstractBaseDaoImpl<Transaction> implements ITransactionDao {
    @Override
    public Transaction insert(Transaction transaction) {
        return create(transaction, Transaction.class);
    }

    @Override
    public List<Transaction> getStatements(String accountNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where(ACCOUNT_NUMBER).is(accountNumber));
        addSortingCriteria(query);
        query.limit(10);
        return find(query, Transaction.class);
    }

    private void addSortingCriteria(Query query) {
        Sort sort = Sort.by(Sort.Direction.DESC, CREATED_AT);
        query.with(sort);
    }
}
