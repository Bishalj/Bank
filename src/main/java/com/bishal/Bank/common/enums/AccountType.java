package com.bishal.Bank.common.enums;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum AccountType {
    SAVING("SAVING", 0.00, 5.00),
    CURRENT("CURRENT", 10000.00, 5.5);


    private String type;
    private Double minimumBalance;
    private Double interestRates;

    AccountType(String type, Double minimumBalance, Double interestRate){
        this.type = type;
        this.minimumBalance = minimumBalance;
        this.interestRates = interestRate;
    }

    public String getType() {
        return type;
    }

    public Double getMinimumBalance() {
        return minimumBalance;
    }

    public Double getInterestRates() {
        return interestRates;
    }

    public static Optional<AccountType> getAccountType(String type){
        if(StringUtils.isEmpty(type))
            return Optional.empty();
        return Arrays.asList(AccountType.values())
                .parallelStream()
                .filter(account -> type.equalsIgnoreCase(account.getType()))
                .findAny();
    }
}
