package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;
import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.service.AccountService;
import org.launchcode.onlinebankapp.service.TransactionService;
import org.launchcode.onlinebankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by User on 7/23/17.
 */
@Controller
@RequestMapping("/transfer")
public class TransferController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");

        return "accounts/transfer-between-accounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(Model model,
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal){
        if (transferFrom.equals("")) {
            model.addAttribute("invalidAccount", true);
            return "accounts/transfer-between-accounts";
        } else if (transferTo.equals("")) {
            model.addAttribute("invalidAccount2", true);
            return "accounts/transfer-between-accounts";
        } else if (accountService.checkValidAmount(amount)) {
            model.addAttribute("invalidAmount", true);
            return "accounts/transfer-between-accounts";
        } else {
            User user = userService.findByUsername(principal.getName());
            CheckingAccount checkingAccount = user.getCheckingAccount();
            SavingsAccount savingsAccount = user.getSavingsAccount();
            transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount, checkingAccount, savingsAccount);
            return "redirect:/home";
        }
    }
}

