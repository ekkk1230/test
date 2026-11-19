package com.example.test.domain;

import com.example.test.Describable;

public class Restaurant implements Describable {
    String storeName;
    int viewCount;
    double rating;
    boolean state;

    @Override
    public String describe() {
        return this.storeName + " / 평점: " + this.rating + " / 조회수: " + this.viewCount + " / 영업 중 " + this.state;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void recordView() {
        this.viewCount = this.viewCount + 1;
    }

    public double getRating() { return this.rating; }

    public int getViewCount() { return this.viewCount; }

    public Restaurant(String storeName, int viewCount, double rating, boolean state) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("평점 범위를 벗어났습니다.");
        }

        this.storeName = storeName;
        this.viewCount = viewCount;
        this.rating = rating;
        this.state = state;
    }
}
