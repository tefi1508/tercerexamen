package com.ucbcba.taller.controllers;


import com.ucbcba.taller.entities.City;
import com.ucbcba.taller.entities.Country;
import com.ucbcba.taller.services.CityService;
import com.ucbcba.taller.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CityController {
    private CityService cityService;
    private CountryService countryService;

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    public void setCountryService(CountryService countryService){
        this.countryService = countryService;
    }

    @RequestMapping(value = "/Cities",method = RequestMethod.GET)
    public String showCities(Model model) {
        Iterable<City> cityList = cityService.listAllCities();
        model.addAttribute("cityList", cityList);
        return "showCities";
    }

    @RequestMapping(value="/countrysearch/{country_id}")
    public String buscar(@PathVariable Integer country_id, Model model){
        Country country = countryService.getCountry(country_id);
        List<City> cityList = country.getCities();
        model.addAttribute("cityList", cityList);
        return "showCities";
    }

}
