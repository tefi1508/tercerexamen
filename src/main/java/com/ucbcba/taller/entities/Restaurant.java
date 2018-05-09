package com.ucbcba.taller.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String direction;
    @NotNull
    private Long phone;
    @NotNull
    private String description;
    @NotNull
    private String city;
    // Falta foto

    public Integer getId() {
        return id;
    }

    public Long getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


}
