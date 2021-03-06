package org.launchcode.onlinebankapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by User on 6/22/17.
 */
@Entity
public class CheckingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "checkingAccount", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    @JsonIgnore
    private List<CheckingTransaction> checkingTransactionList;

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

    public List<CheckingTransaction> getCheckingTransactionList() {
        return checkingTransactionList;
    }

    public void setCheckingTransactionList(List<CheckingTransaction> checkingTransactionList) {
        this.checkingTransactionList = checkingTransactionList;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
