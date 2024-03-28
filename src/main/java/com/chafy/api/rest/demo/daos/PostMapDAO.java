package com.chafy.api.rest.demo.daos;

import com.chafy.api.rest.demo.dto.*;
import com.chafy.api.rest.demo.exceptions.*;

import java.util.*;

public class PostMapDAO implements PostDAO {
    private Map<String, PostDto> postDtos;

    public PostMapDAO() {
        this.postDtos = new HashMap<>();
        this.postDtos.put("1", new PostDto("1", "제목", "테스트입니다."));
        this.postDtos.put("2", new PostDto("2", "제목2", "내용."));
    }

    @Override
    public List<PostDto> findAll() {
        // ID는 순서대로 나오지 않는다.
        return new ArrayList<>(postDtos.values());
    }

    @Override
    public PostDto find(String id) {
        PostDto postDto = postDtos.get(id);
        if (postDto == null) {
            throw new PostNotFound();
        }
        return postDto;
    }

    @Override
    public void save(PostDto postDto) {
        postDtos.put(postDto.getId(), postDto);
    }

    @Override
    public void delete(String id) {
        postDtos.remove(id);
    }
}
