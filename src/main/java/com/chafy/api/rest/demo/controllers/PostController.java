package com.chafy.api.rest.demo.controllers;

import com.chafy.api.rest.demo.application.*;
import com.chafy.api.rest.demo.dto.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServices postService;

    public PostController() {
        postService = new PostServices();
    }

    @GetMapping
    public List<PostDto> list(HttpServletResponse response) {
//      response.addHeader("Access-Control-Allow-Origin", "*");
        List<PostDto> postDtos = postService.getPosts();
        return postDtos;
    }


    //게시물 상세
    //스프링에 내장된 Jackson을 바로 사용할 수 있다.
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable("id") String id) {
        PostDto postDto = postService.getPostDto(id);
        return postDto;
    }

    // 스프링에 내장된 Jackson을 이용한 게시물 생성
    // ObjectMapper의 readValue()를 생략해도 알아서 스프링이 해결해준다.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody(required = false) PostDto postDto) {
        // 스프링에서 자동으로 Jackson을 지원해주니까 예외처리할 필요도 없고 코드도 PostDto를 받아와서 반환하기만 하면된다.
        PostDto created = postService.createPost(postDto);
        return created;
    }

    // 게시물 수정
    @PatchMapping("/{id}")
//    @PutMapping("/{id}")
    public PostDto update(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        PostDto updated = postService.updatePost(id, postDto);
        return updated;
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
        PostDto postDto = postService.deletePost(id);
        return postDto;
    }
}
