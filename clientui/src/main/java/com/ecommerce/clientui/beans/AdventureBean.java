package com.ecommerce.clientui.beans;

import java.util.Date;

public class AdventureBean {

    public AdventureBean() {
    }

    private int id;
    private String name;
    private String description;
    private String image;
    private Date date;
    private int maxEntrant;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMaxEntrant() {
        return maxEntrant;
    }

    public void setMaxEntrant(int maxEntrant) {
        this.maxEntrant = maxEntrant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

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
