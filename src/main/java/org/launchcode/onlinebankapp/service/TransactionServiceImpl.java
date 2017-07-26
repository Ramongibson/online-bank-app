package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.*;
import org.launchcode.onlinebankapp.models.data.CheckingAccountDao;
import org.launchcode.onlinebankapp.models.data.CheckingTransactionDao;
import org.launchcode.onlinebankapp.models.data.SavingsAccountDao;
import org.launchcode.onlinebankapp.models.data.SavingsTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
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

    @Autowired
    private CheckingAccountDao checkingAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

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

    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount){
        if (transferFrom.equalsIgnoreCase("Checking") && transferTo.equalsIgnoreCase("Savings")) {
            checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            checkingAccountDao.save(checkingAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            CheckingTransaction checkingTransaction = new CheckingTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Account", "Finished", Double.parseDouble(amount), checkingAccount.getAccountBalance(), checkingAccount);
            checkingTransactionDao.save(checkingTransaction);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Checking")) {
            checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            checkingAccountDao.save(checkingAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Between account transfer from "+transferFrom+" to "+transferTo, "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }

    }

}
