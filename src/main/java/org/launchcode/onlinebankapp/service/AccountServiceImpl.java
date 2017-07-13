package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;
import org.launchcode.onlinebankapp.models.data.CheckingAccountDao;
import org.launchcode.onlinebankapp.models.data.SavingsAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by User on 7/05/17.
 */
@Service

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
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    private int accountGen() {
        return ++nextAccountNumber;
    }
}
