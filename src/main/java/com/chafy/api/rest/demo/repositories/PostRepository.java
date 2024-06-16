package com.chafy.api.rest.demo.repositories;

import com.chafy.api.rest.demo.dto.*;
import com.chafy.api.rest.demo.exceptions.*;
import com.chafy.api.rest.demo.models.*;

import java.util.*;

public class PostRepository {
    private final Map<PostId, Post> posts;

    public PostRepository() {
        this.posts = new HashMap<PostId, Post>();

        this.posts.put(PostId.of("1"), new Post(PostId.of("1"), "제목", MultiLineText.of("테스트입니다.")));
        this.posts.put(PostId.of("2"), new Post(PostId.of("2"), "제목", MultiLineText.of("내용입니다.")));
    }


    public List<Post> findAll() {
        return new ArrayList<>(posts.values()); // Map의 Collection<V> values()는 Map에 저장된 모든 값을 반환
    }

    public Post find(PostId id) {
        Post post = posts.get(id);
        if(post == null) {
            throw new PostNotFound();
        }
        return post;
    }

    public void save(Post post) {
        posts.put(post.id(), post);
    }

    public void delete(PostId id) {
        posts.remove(id);
    }
}
