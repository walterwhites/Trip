package com.ecommerce.adventure.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
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
    private int category;

    public Adventure() {

    }

    public Adventure(int id, int category, int price, String name, String description, String image, LocalDate date, int maxEntrant) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.name = name;
        this.image = image;
        this.description = description;
        this.date = date;
        this.maxEntrant = maxEntrant;
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "id=" + id +
                "category=" + category +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", maxEntrant=" + maxEntrant +
                '}';
    }
}
