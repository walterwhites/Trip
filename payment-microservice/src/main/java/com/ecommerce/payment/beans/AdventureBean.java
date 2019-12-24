package com.ecommerce.payment.beans;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdventureBean {

    public AdventureBean() {
    }

    private int id;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private int maxEntrant;
    private int price;

    @Override
    public String toString() {
        return "AdventureBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", maxEntrant=" + maxEntrant +
                ", price=" + price +
                '}';
    }
}
