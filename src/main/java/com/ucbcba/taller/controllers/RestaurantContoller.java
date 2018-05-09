package com.ucbcba.taller.controllers;


import com.ucbcba.taller.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestaurantContoller {

    private RestaurantService restaurantService;


    @RequestMapping("/newRestaurant")
    String newRestaurant() {


        return "newRestaurant";
    }

    @RequestMapping("/showRestaurant")
    String showRestaurant() {


        return "showRestaurants";
    }

}
