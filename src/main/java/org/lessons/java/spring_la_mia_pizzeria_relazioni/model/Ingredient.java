package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Enter a name for the ingredient")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private List<Pizza> pizzas;

    public Ingredient() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
}
