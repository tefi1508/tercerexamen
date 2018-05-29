package com.ucbcba.taller.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;

    public Integer getId(){return id;}

    public String getNombre(){return nombre;}

    public void setId(Integer id){this.id=id;}

    public void setNombre(String nombre){this.nombre=nombre;}

    public List<City> getCities(){return cities;}

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
