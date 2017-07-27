package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.models.data.RoleDao;
import org.launchcode.onlinebankapp.models.security.UserRole;
import org.launchcode.onlinebankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 6/22/17.
 */
@Controller
@RequestMapping (value= "/signup")
public class SignUpController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "sign-up";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") @Valid User user, Errors errors, Model model) {

        if(errors.hasErrors()) {
            return "sign-up";
        }
        if (userService.checkUsernameExists(user.getUsername())) {
            model.addAttribute("usernameExists", true);
            return "sign-up";
        }
        if (userService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "sign-up";
        }
        if (userService.checkPasswordsMatch(user.getPassword(), user.getVerifyPassword())) {
            model.addAttribute("passwordMatch", true);
            return "sign-up";
        }
        if (userService.checkEmailsMatch(user.getEmail(), user.getVerifyEmail())) {
            model.addAttribute("emailMatch", true);
            return "sign-up";
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
            userService.createUser(user, userRoles);
            return "redirect:/";
        }
    }
}
