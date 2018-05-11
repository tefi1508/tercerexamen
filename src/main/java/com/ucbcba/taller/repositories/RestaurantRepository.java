package com.ucbcba.taller.repositories;

import com.ucbcba.taller.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}
