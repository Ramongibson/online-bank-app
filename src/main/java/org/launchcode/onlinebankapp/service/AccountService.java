package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;

/**
 * Created by User on 7/05/17.
 */
public interface AccountService {
    CheckingAccount createCheckingAccount();
    SavingsAccount createSavingsAccount();
}
