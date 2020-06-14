package com.bishal.Bank.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "Customer")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Customer {

    @Id
    private String panNumber;
    private Date createdAt;
    @Field(name = "bankAccountNumbers")
    private List<String> bankAccountNumbers;

    public Customer() {
    }

    public Customer(String panNumber, String bankAccountNumber) {
        this.panNumber = panNumber;
        this.bankAccountNumbers = new ArrayList<>();
        this.bankAccountNumbers.add(bankAccountNumber);
        this.createdAt = new Date();
    }
}
