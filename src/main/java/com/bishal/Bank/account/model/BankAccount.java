package com.bishal.Bank.account.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BankAccount")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankAccount {

    @Id
    private String accountNo;
    private Double minimumBalance;
    private Double currentBalance;
    private Double interestRates;
    private String accountType;
    private String panNumber;
    private String branchName;
}
