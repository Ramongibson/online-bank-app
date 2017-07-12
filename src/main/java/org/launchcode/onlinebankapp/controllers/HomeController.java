package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.CheckingAccount;
import org.launchcode.onlinebankapp.models.SavingsAccount;
import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.models.data.RoleDao;
import org.launchcode.onlinebankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by User on 7/12/17.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String home(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        CheckingAccount checkingAccount = user.getCheckingAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("checkingAccount", checkingAccount);
        model.addAttribute("savingsAccount", savingsAccount);

        return "home";
    }


}
