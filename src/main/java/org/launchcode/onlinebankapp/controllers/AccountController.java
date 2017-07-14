package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;
import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.service.AccountService;
import org.launchcode.onlinebankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by User on 7/12/17.
 */

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/checkingAccount")
    public String checkingAccount(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        CheckingAccount checkingAccount = user.getCheckingAccount();
        model.addAttribute("checkingAccount", checkingAccount);
        return "accounts/checking-account";
    }

    @RequestMapping(value = "/savingsAccount")
    public String savingsAccount(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();
        model.addAttribute("savingsAccount", savingsAccount);
        return "accounts/savings-account";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "accounts/deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/home";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");
        return "accounts/withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.withdraw(accountType, Double.parseDouble(amount), principal);
            return "redirect:/home";
    }
}

