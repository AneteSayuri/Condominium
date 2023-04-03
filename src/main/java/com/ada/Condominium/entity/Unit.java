package com.ada.Condominium.entity;

import javax.persistence.*;

@Entity
@Table(name = "table_unit")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apartment;

    public Unit() {
    }

    public Unit(Long id, String apartment) {
        this.id = id;
        this.apartment = apartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
