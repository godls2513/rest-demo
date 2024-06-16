package com.chafy.api.rest.demo.models;

public class Post {
    private PostId id;
    private String title;
    private MultiLineText content;

    public Post(PostId id, String title, MultiLineText content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Post(String title, MultiLineText content) {
        this.id = PostId.generate();
        this.title = title;
        this.content = content;
    }

    // Getters는 절대로 비즈니스 로직을 위해 쓰지 않는다.

    public PostId id() {
        return id;
    }

    public String title() {
        return title;
    }

    public MultiLineText content() {
        return content;
    }

    public void update(String title, MultiLineText content) {
        this.title = title;
        this.content = content;
    }
}
