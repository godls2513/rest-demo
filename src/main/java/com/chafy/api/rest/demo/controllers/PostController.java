package com.chafy.api.rest.demo.controllers;

import com.chafy.api.rest.demo.dto.*;
import com.fasterxml.jackson.databind.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final ObjectMapper objectMapper;

    public PostController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    List<PostDto> postDtos = new ArrayList<>(List.of(
            new PostDto("123", "제목", "테스트입니다."),
            new PostDto("456", "제목2", "2번테스트입니다.")
    ));

    // jackson을 이용한 게시물 목록 구현하는 코드
    @GetMapping
//    @CrossOrigin
    public List<PostDto> list(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
        return postDtos;
    }


    //게시물 상세
    //스프링에 내장된 Jackson을 바로 사용할 수 있다.
    @GetMapping("/{id}")
    public PostDto detail(@PathVariable("id") String id) {
        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");
        return postDto;
    }

    // 스프링에 내장된 Jackson을 이용한 게시물 생성
    // ObjectMapper의 readValue()를 생략해도 알아서 스프링이 해결해준다.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody(required = false) PostDto postDto) {
        // 스프링에서 자동으로 Jackson을 지원해주니까 예외처리할 필요도 없고 코드도 PostDto를 받아와서 반환하기만 하면된다.
        postDto.setId("1004");
        postDtos.add(postDto);
        return postDto;
    }

    // 게시물 수정
//  @PatchMapping("/{id}")
    @PutMapping("/{id}")
    public PostDto update(@PathVariable("id") String id, @RequestBody PostDto postDto) {
        postDto.setId(id);
        return postDto;
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
        PostDto postDto = postDtos.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .get();

        postDtos.remove(postDto);

        postDtos.remove(id);
        return postDto;
    }
}
