package com.example.test;

import com.example.test.domain.Restaurant;

public class Review implements Describable {
    private Restaurant restaurant;
    private String content;

    @Override
    public String describe() {
        String storeName = restaurant.getStoreName();
        return storeName + " / " + this.content;
    }

    public Review(Restaurant restaurant, String content) {
        this.restaurant = restaurant;
        this.content = content;
    }
}
