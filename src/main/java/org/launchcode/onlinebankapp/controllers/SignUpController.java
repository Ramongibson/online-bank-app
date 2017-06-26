package org.launchcode.onlinebankapp.controllers;

import org.launchcode.onlinebankapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User on 6/26/17.
 */
@Controller
@RequestMapping(value="signup")
public class SignUpController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup/index";
    }

}
