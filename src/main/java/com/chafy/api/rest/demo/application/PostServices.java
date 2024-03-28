package com.chafy.api.rest.demo.application;

import com.chafy.api.rest.demo.daos.*;
import com.chafy.api.rest.demo.dto.*;
import com.github.f4b6a3.tsid.*;

import java.util.*;

public class PostServices {
    private final PostDAO postDAO;

    public PostServices() {
        this.postDAO = new PostListDAO();
//        this.postDAO = new PostMapDAO();
    }

    public List<PostDto> getPosts() {
        return postDAO.findAll();
    }

    public PostDto getPostDto(String id) {
        return postDAO.find(id);
    }

    public PostDto createPost(PostDto postDto) {
        // TODO: 원본을 고치지 말것

        //String id = UUID.randomUUID().toString(); // UUID : 자바에서 제공 sorting이 되어있지 않음
        //String id = UlidCreator.getUlid().toString(); // ULID : UUID를 확장한 라이브러리, sorting이 되어있음
        String id = TsidCreator.getTsid().toString(); // TSID : ULID를 확장한 라이브러리, sorting이 되어있고, ULID보다 더 간결
        PostDto newPostDto = new PostDto();
        newPostDto.setId(id);
        newPostDto.setTitle(postDto.getTitle());
        newPostDto.setContent(postDto.getContent());

        postDAO.save(newPostDto);
        return newPostDto;
    }

    public PostDto updatePost(String id, PostDto postDto) {
        PostDto found = postDAO.find(id);
        // TODO: 별로지만 일단 한다.
        found.setTitle(postDto.getTitle());
        found.setContent(postDto.getContent());

        return found;
    }

    public PostDto deletePost(String id) {
        PostDto postDto = postDAO.find(id);
        postDAO.delete(id);
        return postDto;
    }
}
