package com.ucbcba.taller.controllers;

import com.ucbcba.taller.services.RestaurantService;
import com.ucbcba.taller.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "restaurants";
    }

    @RequestMapping("restaurant/{id}")
    public String showRestaurant(@PathVariable Integer id,Model model){
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        return "restaurantshow";
    }

    @RequestMapping("restaurant/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        return "restaurantform";
    }

    @RequestMapping("restaurant/new")
    public String newRestaurant(Model model){
        model.addAttribute("restaurant", new Restaurant());
        return "restaurantform";
    }

    @RequestMapping(value = "restaurant",method = RequestMethod.POST)
    public String saveRestaurant(Restaurant restaurant){
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant/" + restaurant.getId();
    }

    @RequestMapping("restaurant/delete/{id}")
    public String delete(@PathVariable Integer id){
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurants";
    }

}
