package com.bishal.Bank.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Transaction")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Transaction {
    @Id
    private String transactionId;
    private Double transactionAmount;
    private String type;
    private String accountNumber;
    private Date createdAt;

    public Transaction() {
    }

    public Transaction(Double transactionAmount, String type, String accountNumber) {
        this.transactionAmount = transactionAmount;
        this.type = type;
        this.accountNumber = accountNumber;
        this.createdAt = new Date();
    }
}
