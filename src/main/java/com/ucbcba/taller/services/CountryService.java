package com.ucbcba.taller.services;

import com.ucbcba.taller.entities.Country;

public interface CountryService {
    Iterable<Country> listAllCountries();

    void saveCountry(Country country);

    Country getCountry(Integer id);

    void deleteCountry(Integer id);
}
