package org.launchcode.onlinebankapp.models;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 6/22/17.
 */
public class SavingsAccount {
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    private List<SavingsTransaction> savingsTransactionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<SavingsTransaction> getCheckingTransactionList() {
        return savingsTransactionList;
    }

    public void setCheckingTransactionList(List<SavingsTransaction> checkingTransactionList) {
        this.savingsTransactionList = checkingTransactionList;
    }
}
