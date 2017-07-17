package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.CheckingTransaction;
import org.launchcode.onlinebankapp.models.SavingsTransaction;

import java.util.List;

/**
 * Created by User on 7/15/17.
 */
public interface TransactionService {

    List<CheckingTransaction> findCheckingTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void saveCheckingDepositTransaction(CheckingTransaction checkingTransaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

    void saveCheckingWithdrawTransaction(CheckingTransaction checkingTransaction);

    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
}
