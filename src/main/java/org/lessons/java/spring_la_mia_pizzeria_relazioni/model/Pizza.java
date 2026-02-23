package org.lessons.java.spring_la_mia_pizzeria_relazioni.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Enter name of pizza")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Enter description of pizza")
    @Column(nullable = false, length = 200)
    private String description;

    @Column(name = "url_img", length = 200)
    @Size(max = 200)
    private String urlImg;

    @NotNull(message = "Enter price")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers;

    @ManyToMany
    @JoinTable(name = "ingredient_pizza", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public Pizza() {
    }

    public Pizza(Integer id, String name, String description, String urlImg, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.urlImg = urlImg;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return this.specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }
}
