package com.ucbcba.taller.repositories;

import com.ucbcba.taller.entities.Country;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CountryRepository extends CrudRepository<Country, Integer> {
}
