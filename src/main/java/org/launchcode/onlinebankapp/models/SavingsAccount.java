package org.launchcode.onlinebankapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 6/22/17.
 */
@Entity
public class SavingsAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany (mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @Transient
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

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
