package com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    public static List<Item> cart = new ArrayList<>();

    public static void addItem(Item item) {
        cart.add(item);
    }

}
