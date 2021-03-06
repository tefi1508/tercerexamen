package com.ucbcba.taller.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ucbcba.taller.entities.*;
import com.ucbcba.taller.services.CategoryService;
import com.ucbcba.taller.services.CityService;
import com.ucbcba.taller.services.RestaurantService;
import com.ucbcba.taller.services.UploadFileService;
import com.ucbcba.taller.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RestaurantContoller {

    private RestaurantService restaurantService;
    private CategoryService categoryService;
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    private UploadFileService uploadFileService;


    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    String save(@RequestParam("file")MultipartFile file,Restaurant restaurant) {
        try {

            uploadFileService.saveFile(file ,restaurant.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/Restaurants";
    }

    @RequestMapping("/newRestaurant")
    String newRestaurant(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()) {
            Iterable<Category> categories = categoryService.listAllCategories();
            model.addAttribute("categories", categories);
            Iterable<City> cities = cityService.listAllCities();
            model.addAttribute("cities", cities);
            return "newRestaurant";
        }
        else {
            return "redirect:/Restaurants";
        }
    }

    @RequestMapping(value = "/Restaurants",method = RequestMethod.GET)
    public String showRest(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        Iterable<Restaurant> restList = restaurantService.listAllRestaurants();
        model.addAttribute("restList", restList);
        return "showRestaurants";
    }

    @RequestMapping(value = "/publicRestaurants",method = RequestMethod.GET)
    public String showRestPub(Model model) {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());*/
        Iterable<Restaurant> restList = restaurantService.listAllRestaurants();
        model.addAttribute("restList", restList);
        return "showRestaurantsPublic";
    }

    @RequestMapping(value = "/userRestaurants",method = RequestMethod.GET)
    public String showRestUser(Model model) {
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());*/
        Iterable<Restaurant> restList = restaurantService.listAllRestaurants();
        model.addAttribute("restList", restList);
        return "showRestaurantsUser";
    }

    @RequestMapping(value = "/showRestaurants",method = RequestMethod.GET)
    public String showRestaurants(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()) {
            return "redirect:/Restaurants";
        }
        else{
            return "redirect:/userRestaurants";
        }
    }

    @RequestMapping("/showRestaurant/{id}")
    String showRes(@PathVariable Integer id, Model model) {
        Restaurant rest = restaurantService.getRestaurant(id);
        model.addAttribute("rest", rest);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());


        model.addAttribute("comment",new Comment(rest,user));
        model.addAttribute("use", user);
        return "showRestaurant";
    }

    @RequestMapping("/showRestaurantPublic/{id}")
    String showRest(@PathVariable Integer id, Model model) {
        Restaurant rest = restaurantService.getRestaurant(id);
        model.addAttribute("rest", rest);
        return "showRestaurantPublic";
    }

    @RequestMapping("/showRestaurantAdmin/{id}")
    String showRestAdmin(@PathVariable Integer id, Model model) {
        Restaurant rest = restaurantService.getRestaurant(id);
        model.addAttribute("rest", rest);
        return "showRestaurantAdmin";
    }

    @RequestMapping("/showRestaurantUser/{id}")
    String showRestUser(@PathVariable Integer id, Model model) {
        Restaurant rest = restaurantService.getRestaurant(id);
        model.addAttribute("rest", rest);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()){
            return "showRestaurantAdmin";
        }
        else{
            return "redirect:/showRestaurant/"+id;
        }
    }

    @RequestMapping("/deleteRestaurant/{id}")
    String delete(@PathVariable Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()) {
            restaurantService.deleteRestaurant(id);
        }
        return "redirect:/Restaurants";
    }

    @RequestMapping("/editRestaurant/{id}")
    String editPost(@PathVariable Integer id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()) {
            Restaurant rest = restaurantService.getRestaurant(id);
            Iterable<Category> categories = categoryService.listAllCategories();
            model.addAttribute("categories", categories);
            Iterable<City> cities = cityService.listAllCities();
            model.addAttribute("cities", cities);
            model.addAttribute("rest", rest);
            return "editRestaurant";
        }
        else{
            return "redirect:/Restaurants";
        }
    }

    @RequestMapping("/like/{id}")
    String like(@PathVariable Integer id) {
        Restaurant res = restaurantService.getRestaurant(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if (res.findUserLike(user) == false) {
            res.addLikke(user);
            res.setLikes(res.getLikes()+1);
            restaurantService.saveRestaurant(res);
        }
        return "redirect:/showRestaurant/" + res.getId();
    }

    @RequestMapping(value="/search/{name}", method = RequestMethod.GET)

    public String buscarRestaurant(@PathVariable("name") String name, Model model){
        //Restaurant restaurant = restaurantService.findRestaurantByName(name);
//        model.addAttribute("restaurant", restaurant);
//        return "redirect:/showRestaurant/"+ restaurant.getId();

        List<Restaurant> restaurants=(List<Restaurant>)restaurantService.listAllRestaurants();
        List<Restaurant> aux= new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getName().contains(name)){
                aux.add(restaurant);
            }
        }
        model.addAttribute("restList", aux);
        return "showRestaurantsUser";

    }

    @RequestMapping(value="/publicSearch/{name}", method = RequestMethod.GET)
    public String buscarRestaurantPublic(@PathVariable("name") String name, Model model){
//        Restaurant restaurant = restaurantService.findRestaurantByName(name);
//        model.addAttribute("restaurant", restaurant);
//        return "redirect:/showRestaurantPublic/"+ restaurant.getId();
        List<Restaurant> restaurants=(List<Restaurant>)restaurantService.listAllRestaurants();
        List<Restaurant> aux= new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getName().contains(name)){
                aux.add(restaurant);
            }
        }
        model.addAttribute("restList", aux);
        return "showRestaurantsUser";
    }

    @RequestMapping(value="/categorysearch/{category_id}")
    public String buscar(@PathVariable Integer category_id, Model model){
       Category category = categoryService.getCategory(category_id);
       List<Restaurant> restList = category.getRestaurantList();
        model.addAttribute("restList", restList);
       return "showRestaurantsPublic";
   }

    @RequestMapping(value="/usercategorysearch/{category_id}")
    public String buscarusuario(@PathVariable Integer category_id, Model model){
        Category category = categoryService.getCategory(category_id);
        List<Restaurant> restList = category.getRestaurantList();
        model.addAttribute("restList", restList);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()){
            return "showRestaurants";
        }
        else{
            return "showRestaurantsUser";
        }
    }

    @RequestMapping(value="/citysearch/{city_id}")
    public String buscarCiudad(@PathVariable Integer city_id, Model model){
        City city=cityService.getCity(city_id);
        List<Restaurant> resList=city.getRestaurantList();
        model.addAttribute("restList", resList);
        return  "showRestaurantsPublic";
    }

    @RequestMapping(value="/usercitysearch/{city_id}")
    public String buscarCiudadUser(@PathVariable Integer city_id, Model model){
        City city=cityService.getCity(city_id);
        List<Restaurant> resList=city.getRestaurantList();
        model.addAttribute("restList", resList);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        if(user.isAdmin()){
            return "showRestaurants";
        }
        else{
            return "showRestaurantsUser";
        }
    }

}
