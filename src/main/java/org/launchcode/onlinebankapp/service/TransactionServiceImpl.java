package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.CheckingTransaction;
import org.launchcode.onlinebankapp.models.SavingsTransaction;
import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.models.data.CheckingTransactionDao;
import org.launchcode.onlinebankapp.models.data.SavingsTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by User on 7/15/17.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckingTransactionDao checkingTransactionDao;

    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

    public List<CheckingTransaction> findCheckingTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<CheckingTransaction> checkingTransactionList = user.getCheckingAccount().getCheckingTransactionList();

        return checkingTransactionList ;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

        return savingsTransactionList;
    }
    public void saveCheckingDepositTransaction(CheckingTransaction checkingTransaction) {
        checkingTransactionDao.save(checkingTransaction);
    }

    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

    public void saveCheckingWithdrawTransaction(CheckingTransaction checkingTransaction) {
        checkingTransactionDao.save(checkingTransaction);
    }

    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
        savingsTransactionDao.save(savingsTransaction);
    }

}
