package com.ecommerce.clientui.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryBean {

    private int id;
    private String name;
    private String color;

    public CategoryBean() {
    }

    public CategoryBean(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "AdventureBean{" +
                "id=" + id +
                ", name=" + name +
                ", color='" + color + '}';
    }
}
