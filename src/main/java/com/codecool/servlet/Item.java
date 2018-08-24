package com.codecool.servlet;

public class Item {

    int id;
    String name;
    double price;

    static int itemNum = 0;


    public Item(String name, double price) {
        itemNum++;
        this.id = itemNum;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
