package com.ucbcba.taller.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nombre;

    public String getNombre() { return nombre;   }

    public void setNombre(String nombre){this.nombre=nombre;}

    public Integer getId(){return id;}

    public void setId(Integer id){this.id=id;}
}
