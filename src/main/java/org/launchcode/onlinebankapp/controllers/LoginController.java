package org.launchcode.onlinebankapp.controllers;


import org.launchcode.onlinebankapp.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by User on 6/22/17.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login")
    public String index() {
        return "login/index";
    }
}

