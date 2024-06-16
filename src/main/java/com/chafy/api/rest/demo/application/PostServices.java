package com.chafy.api.rest.demo.application;

import com.chafy.api.rest.demo.dto.*;
import com.chafy.api.rest.demo.models.*;
import com.chafy.api.rest.demo.repositories.*;

import java.util.*;

public class PostServices {
    //    private final PostDAO postDAO;
    private final PostRepository postRepository;

    public PostServices() {
        postRepository = new PostRepository();
//        this.postDAO = new PostListDAO();
//        this.postDAO = new PostMapDAO();
    }

    public List<PostDto> getPosts() {
//        return postDAO.findAll();

        List<Post> posts = postRepository.findAll();

        // 스트림의 map은 요소A를 요소B로 변환하는 역할을 한다.
        // 그래서 스트림의 요소인 post를 PostDto 객체로 변환하여 list로 반환한다.
        return posts.stream().map(post -> new PostDto(post)).toList();
    }

    public PostDto getPostDto(String id) {
//        return postDAO.find(id);

        Post post = postRepository.find(PostId.of(id));
        return new PostDto(post);
    }

    public PostDto createPost(PostDto postDto) {
        // TODO: 원본을 고치지 말것

//        String id = UUID.randomUUID().toString(); // UUID : 자바에서 제공 sorting이 되어있지 않음
//        String id = UlidCreator.getUlid().toString(); // ULID : UUID를 확장한 라이브러리, sorting이 되어있음
//        String id = TsidCreator.getTsid().toString(); // TSID : ULID를 확장한 라이브러리, sorting이 되어있고, ULID보다 더 간결
//        PostDto newPostDto = new PostDto();
//        newPostDto.setId(id);
//        newPostDto.setTitle(postDto.getTitle());
//        newPostDto.setContent(postDto.getContent());
//
//        postDAO.save(newPostDto);
//        return newPostDto;

        Post post = new Post(postDto.getTitle(), MultiLineText.of(postDto.getContent()));
        postRepository.save(post);
        return new PostDto(post);
    }

    public PostDto updatePost(String id, PostDto postDto) {
//        PostDto found = postDAO.find(id);
//        found.setTitle(postDto.getTitle());
//        found.setContent(postDto.getContent());
//
//        return found;
        Post post = postRepository.find(PostId.of(id));
        post.update(
                postDto.getTitle(),
                MultiLineText.of(postDto.getContent())
        );
        return new PostDto(post);
    }

    public PostDto deletePost(String id) {
//        PostDto postDto = postDAO.find(id);
//        postDAO.delete(id);
//        return postDto;

        Post post = postRepository.find(PostId.of(id));
        postRepository.delete(PostId.of(id));
        return new PostDto(post);
    }
}
