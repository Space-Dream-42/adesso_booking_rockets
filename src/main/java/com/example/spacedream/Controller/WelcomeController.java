package com.example.spacedream.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;


@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping
    public String getWelcomeView(Model model) {
        return "welcome";
    }
}
