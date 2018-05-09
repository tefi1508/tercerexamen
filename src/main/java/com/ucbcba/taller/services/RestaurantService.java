package com.ucbcba.taller.services;

import com.ucbcba.taller.entities.Restaurant;

import javax.persistence.criteria.CriteriaBuilder;

public interface RestaurantService {
    Iterable<Restaurant> listAllRestaurants();
    Restaurant getRestaurantById(Integer id);
    Restaurant saveRestaurant(Restaurant restaurant);
    void deleteRestaurant (Integer id);
}
