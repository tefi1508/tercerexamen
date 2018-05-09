package com.ucbcba.taller.repositories;

import com.ucbcba.taller.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {
}
