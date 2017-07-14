package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.*;
import org.launchcode.onlinebankapp.models.data.CheckingAccountDao;
import org.launchcode.onlinebankapp.models.data.SavingsAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

/**
 * Created by User on 7/05/17.
 */
@Service
@Transactional

public class AccountServiceImpl implements AccountService {

    private static int nextAccountNumber = 11223167;

    @Autowired
    private CheckingAccountDao checkingAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;

    public CheckingAccount createCheckingAccount() {
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setAccountBalance(new BigDecimal(0.0));
        checkingAccount.setAccountNumber(accountGen());

        checkingAccountDao.save(checkingAccount);

        return checkingAccountDao.findByAccountNumber(checkingAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(500.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    public void deposit(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Checking")) {
            CheckingAccount checkingAccount = user.getCheckingAccount();
            checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().add(new BigDecimal(amount)));
            checkingAccountDao.save(checkingAccount);

            Date date = new Date();

            CheckingTransaction checkingTransaction = new CheckingTransaction(date, "Deposit to Checking Account", "Account", "Finished", amount, checkingAccount.getAccountBalance(), checkingAccount);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to Savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
        }
    }
    public void withdraw(String accountType, double amount, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (accountType.equalsIgnoreCase("Checking")) {
            CheckingAccount checkingAccount = user.getCheckingAccount();
            checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            checkingAccountDao.save(checkingAccount);

            Date date = new Date();

            CheckingTransaction checkingTransaction = new CheckingTransaction(date, "Withdraw from Checking Account", "Account", "Finished", amount, checkingAccount.getAccountBalance(), checkingAccount);

        } else if (accountType.equalsIgnoreCase("Savings")) {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from Savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);

        }
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
