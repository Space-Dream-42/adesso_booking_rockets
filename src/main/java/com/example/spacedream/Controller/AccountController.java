package com.example.spacedream.Controller;

import com.example.spacedream.Entities.Customer;
import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Service.CustomerService;
import com.example.spacedream.Service.SpacemanagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SpacemanagerService spacemanagerService;


    @GetMapping("/customers")
    public String getCustomerCreationView(Model model) {
        return "createCustomerAccount";
    }


    @PostMapping("/customers")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.createCustomer(customer);
        return "accountCreated";
    }


    @GetMapping("/spacelines")
    public String getSpacemanagerCreationView(Model model) {
        return "createSpacemanagerAccount";
    }


    @PostMapping("/spacelines")
    public String createSpacemanager(@ModelAttribute Spacemanager spacemanager, Model model) {
        spacemanagerService.createSpacemanager(spacemanager);
        return "accountCreated";
    }
}
