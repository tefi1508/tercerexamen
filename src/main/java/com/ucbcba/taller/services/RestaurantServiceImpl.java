package com.ucbcba.taller.services;

import com.ucbcba.taller.entities.Restaurant;
import com.ucbcba.taller.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Iterable<Restaurant> listAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Integer id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        restaurantRepository.delete(id);
    }
}
