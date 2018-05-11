package com.ucbcba.taller.controllers;


import com.ucbcba.taller.entities.Restaurant;
import com.ucbcba.taller.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestaurantContoller {

    private RestaurantService restaurantService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    String save(Restaurant restaurant) {

        restaurantService.saveRestaurant(restaurant);
        return "redirect:/bienvenidos";
    }

    @RequestMapping("/newRestaurant")
    String newRestaurant() {


        return "newRestaurant";
    }

    @RequestMapping(value = "/Restaurants",method = RequestMethod.GET)
    public String showRest(Model model) {
        Iterable<Restaurant> restList = restaurantService.listAllRestaurants();
        model.addAttribute("restList",restList);
        return "showRestaurants";
    }

}
