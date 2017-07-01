package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User on 6/22/17.
 */
@Controller
@RequestMapping (value= "/signup")
public class SignUpController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "signup/sign-up";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") User user, Model model) {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup/sign-up";
        } else {
            userService.save(user);

            return "redirect:/";
        }
    }
}
