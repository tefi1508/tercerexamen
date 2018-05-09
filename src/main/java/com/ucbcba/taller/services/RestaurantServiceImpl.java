package com.ucbcba.taller.services;

import com.sun.jnlp.IntegrationServiceImpl;
import com.ucbcba.taller.entities.Restaurant;
import com.ucbcba.taller.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Restaurant> listAllRestaurants(){
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById (Integer id){
        return restaurantRepository.findOne(id);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant (Integer id){
        restaurantRepository.delete(id);
    }
}
