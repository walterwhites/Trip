package com.ecommerce.adventure.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Adventure {

    @Id
    @GeneratedValue
    private int id;

    @Length(min = 3, max = 50)
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private int maxEntrant;
    private int price;

    public Adventure() {

    }

    public Adventure(int id, int price, String name, String description, String image, LocalDate date, int maxEntrant) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.image = image;
        this.description = description;
        this.date = date;
        this.maxEntrant = maxEntrant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMaxEntrant() {
        return maxEntrant;
    }

    public void setMaxEntrant(int maxEntrant) {
        this.maxEntrant = maxEntrant;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", maxEntrant=" + maxEntrant +
                '}';
    }
}
