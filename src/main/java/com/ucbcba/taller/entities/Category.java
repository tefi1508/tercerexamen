package com.ucbcba.taller.entities;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Restaurant> restaurantList;

    public String getNombre() { return nombre;   }

    public void setNombre(String nombre){this.nombre=nombre;}

    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}

    public void setRestaurantList(List<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }

    public List<Restaurant> getRestaurantList(){
        return restaurantList;
    }

}
