package com.example.spacedream.Controller;

import com.example.spacedream.Service.LoginService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/logins")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping("/customers")
    public String getCustomerLoginView(Model model) {
        model.addAttribute("actorType", "customers");
        return "login";
    }


    @GetMapping("/spacelines")
    public String getSpacemanagerLoginView(Model model) {
        model.addAttribute("actorType", "spacelines");
        return "login";
    }

    @PostMapping("/logouts")
    public String setAllToLogout(Model model) {
        loginService.customerLogout();
        loginService.spacemanagerLogout();
        return "logout";
    }
}