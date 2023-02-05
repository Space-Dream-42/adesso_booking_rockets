package com.example.spacedream.Service;

import com.example.spacedream.Controller.LoginController;
import com.example.spacedream.Entities.Customer;
import com.example.spacedream.Entities.Spacemanager;
import com.example.spacedream.Exceptions.StillLoggedInException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;


@Service
public class LoginService {
    private Spacemanager spacemanager;
    private Customer customer;


    public void spacemanagerLogin(Spacemanager spacemanager) throws StillLoggedInException {
        if (this.spacemanager == null) {
            this.spacemanager = spacemanager;
        } else {
            throw new StillLoggedInException("A spacemanager is still logged in! Please log the spacemanager out.");
        }
    }


    public void customerLogin(Customer customer) throws StillLoggedInException {
        if (this.customer == null) {
            this.customer = customer;
        } else {
            throw new StillLoggedInException("A customer is still logged in! Please log the customer out.");
        }
    }


    public void spacemanagerLogout() {
        this.spacemanager = null;
    }


    public void customerLogout() {
        this.customer = null;
    }


    public Customer getCustomer() {
        return this.customer;
    }


    public Spacemanager getSpacemanager() {
        return this.spacemanager;
    }
}
