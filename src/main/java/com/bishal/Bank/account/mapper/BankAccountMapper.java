package com.bishal.Bank.account.mapper;

import com.bishal.Bank.common.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BankAccountMapper {
    private String accountType;
    private String panNumber;
    private Double amount;
    private String branchName;
}
