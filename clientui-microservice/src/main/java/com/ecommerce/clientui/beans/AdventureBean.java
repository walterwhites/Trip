package com.ecommerce.clientui.beans;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdventureBean {

    public AdventureBean() {
    }

    private int id;
    private int category;
    private String name;
    private String description;
    private String image;
    private LocalDate date;
    private int maxEntrant;
    private int price;

    private String categoryName;
    private String categoryColor;

    @Override
    public String toString() {
        return "AdventureBean{" +
                "id=" + id +
                ", category=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", maxEntrant=" + maxEntrant +
                ", price=" + price +
                ", categoryName=" + categoryName +
                ", categoryColor=" + categoryColor +
                '}';
    }
}
