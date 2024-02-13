package com.chafy.api.rest.demo.controllers;

import com.chafy.api.rest.demo.dto.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/items")
public class PostJacksonController {
    private final ObjectMapper objectMapper;

    public PostJacksonController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // jackson을 이용한 게시물 목록 구현하는 코드
    @GetMapping("/jackson")
    public List<PostDto> list2() {
        List<PostDto> postDto = List.of(
                new PostDto("123", "제목", "테스트입니다."),
                new PostDto("456", "제목2", "2번테스트입니다.")
        );
        return postDto;
    }

    // ObjectMapper를 이용한 게시물 상세
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") String id) throws JacksonException {
        PostDto postDto = new PostDto(id, "제목", "내용이다.");
        String s = objectMapper.writeValueAsString(postDto);
        return s;
    }

    //게시물 상세
    //스프링에 내장된 Jackson을 바로 사용할 수 있다.
    @GetMapping("/jackson/{id}")
    public PostDto detailJackson(@PathVariable("id") String id) {
        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");

        return postDto;
    }

    // Jackson을 이용한 게시물 생성
    @PostMapping("/jackson")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createJackson(@RequestBody(required = false) String body) throws JacksonException {
        PostDto postDto = objectMapper.readValue(body, PostDto.class); // readValue : 지정된 JSON 콘텐츠 문자열에서 JSON 콘텐츠를 역직렬화하는 방법
        return postDto;
    }

    // 스프링에 내장된 Jackson을 이용한 게시물 생성
    // ObjectMapper의 readValue()를 생략해도 알아서 스프링이 해결해준다.
    @PostMapping("/springJackson")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createJackson2(@RequestBody(required = false) PostDto postDto) {
        // 스프링에서 자동으로 Jackson을 지원해주니까 예외처리할 필요도 없고 코드도 PostDto를 받아와서 반환하기만 하면된다.
        return postDto;
    }

    // 게시물 수정
//  @PatchMapping("/{id}")
    @PutMapping("/{id}")
//    @PatchMapping("/{id}")
    public PostDto update(@PathVariable("id") String id) {

        PostDto postDto = new PostDto(id, "제목", "내용");
        return postDto;
    }

    // 게시물 삭제
    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable("id") String id) {
        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");
        return postDto;
    }
}
