package com.example.spacedream.Controller;

import com.example.spacedream.Entities.Mission;
import com.example.spacedream.Exceptions.StillLoggedInException;
import com.example.spacedream.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Controller
@RequestMapping("/views")
public class ViewController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SpacemanagerService spacemanagerService;
    @Autowired
    private MissionService missionService;
    @Autowired
    private LoginService loginService;


    @PostMapping("/customers")
    public String setThisCustomerAsLoginAndGetCustomerView(Model model, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (customerService.validCustomerAndPassword(userName, password)) {
            try {
                loginService.customerLogin(customerService.getCustomer(userName).get());
                model.addAttribute("customerName", loginService.getCustomer().getName());
                return "customerView";
            } catch (StillLoggedInException stillLoggedInException) {
                model.addAttribute("errorMessage", stillLoggedInException.getMessage());
                return "error";
            }
        } else {
            model.addAttribute("loginErrorMessage", "The password or username is incorrect.");
            model.addAttribute("actorType","customers");
            return "login";
        }
    }


    @GetMapping("/customers")
    public String getCustomerView(Model model) {
        model.addAttribute("customerName", loginService.getCustomer().getName());
        return "customerView";
    }


    @GetMapping("/search-results")
    public String getSearchResults(@RequestParam("startPlanet") String startPlanet, @RequestParam("targetPlanet") String targetPlanet, Model model) {
        List<Mission> searchResults = missionService.getMissionsBySearchQuery(startPlanet, targetPlanet);
        model.addAttribute("missions", searchResults);
        return "searchResults";
    }


    @GetMapping("/overviews")
    public String getTheOverviewOfMissionToBook(@RequestParam("id") int id, Model model) {
        model.addAttribute("missionToBook", missionService.findMissionById(id));
        return "overview";
    }


    @PostMapping("/orders-completed")
    public String decreaseAmountOfticketsAndGetOrderCompletedView(@RequestParam("id") int id, Model model) {
        Mission missionToBook = missionService.findMissionById(id);
        missionToBook.setAmountOfTickets(missionToBook.getAmountOfTickets() - 1);
        loginService.getCustomer().setMission(missionToBook);
        try {
            missionService.updateMission(missionToBook);
            customerService.updateCustomer(loginService.getCustomer());
        } catch (EntityNotFoundException entityNotFoundException) {
            model.addAttribute("errorMessage", entityNotFoundException.getMessage());
            return "error";
        }
        return "orderCompleted";
    }


    @PostMapping("/spacelines")
    public String setThisSpacemanagerAsLoginAndGetSpacemanagerView(Model model, @RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (spacemanagerService.validSpacemanagerAndPassword(userName, password)) {
            try {
                loginService.spacemanagerLogin(spacemanagerService.getSpacemanager(userName).get());
                buildModel(model);
                return "spacelineView";
            } catch (StillLoggedInException stillLoggedInException) {
                model.addAttribute("errorMessage", stillLoggedInException.getMessage());
                return "error";
            }
        } else {
            model.addAttribute("loginErrorMessage", "The password or username is incorrect.");
            model.addAttribute("actorType","spacelines");
            return "login";
        }
    }


    @PostMapping("/spacelines/create-missions")
    public String addMissionToDatabase(@ModelAttribute Mission mission, Model model) {
        mission.setSpacemanager(loginService.getSpacemanager());
        missionService.createMission(mission);
        buildModel(model);
        return "spacelineView";
    }


    private void buildModel(Model model) {
        model.addAttribute("missions", missionService.getMissionsBySpacemanagerUserName(loginService.getSpacemanager().getUserName()));
        model.addAttribute("spacemangerUserName", loginService.getSpacemanager().getSpacelineName());
    }
}