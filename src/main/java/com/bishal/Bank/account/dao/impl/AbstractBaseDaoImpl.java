package com.bishal.Bank.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public abstract class AbstractBaseDaoImpl<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    public T create(T t, Class<T> clazz){
        return mongoTemplate.insert(t, clazz.getSimpleName());
    }

    public void update(Query query, Update update, Class<T> clazz){
        mongoTemplate.updateFirst(query, update, clazz.getSimpleName());
    }

    public T findById(String id, Class<T> clazz){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, clazz);
    }
}
