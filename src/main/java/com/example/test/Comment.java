package com.example.test;

public class Comment extends TextPost {
    private String authorName;

    Comment(String content, String authorName) {
        super(content);
        this.authorName = authorName;
    }

    @Override
    String describe() {
        return this.authorName + ": " + super.describe();
    }
}
