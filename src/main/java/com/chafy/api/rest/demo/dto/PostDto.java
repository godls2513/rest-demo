package com.chafy.api.rest.demo.dto;

import com.chafy.api.rest.demo.models.*;

public class PostDto {
    private String id;
    private String title;
    private String content;

    public PostDto() {
    }

    public PostDto(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDto(Post post) {
        this(post.id().toString(), post.title(), post.content().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
