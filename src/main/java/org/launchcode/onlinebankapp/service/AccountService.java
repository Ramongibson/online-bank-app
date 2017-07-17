package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;

import java.security.Principal;

/**
 * Created by User on 7/05/17.
 */
public interface AccountService {
    CheckingAccount createCheckingAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
    boolean checkValidAmount(String amount);
}
