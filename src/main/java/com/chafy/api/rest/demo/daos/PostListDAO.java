package com.chafy.api.rest.demo.daos;

import com.chafy.api.rest.demo.dto.*;
import com.chafy.api.rest.demo.exceptions.*;

import java.util.*;

public class PostListDAO implements PostDAO {
    private final List<PostDto> postDtos;

    public PostListDAO() {
        this.postDtos = new ArrayList<>();
        this.postDtos.add(new PostDto("1", "제목", "테스트입니다."));
        this.postDtos.add(new PostDto("2", "제목2", "내용."));
    }

    @Override
    public List<PostDto> findAll() {
        return new ArrayList<>(postDtos);
    }

    @Override
    public PostDto find(String id) {
        return postDtos.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PostNotFound());
    }

    @Override
    public void save(PostDto postDto) {
        postDtos.add(postDto);
    }

    @Override
    public void delete(String id) {
        PostDto postDto = find(id);
        postDtos.remove(postDto);
    }
}
