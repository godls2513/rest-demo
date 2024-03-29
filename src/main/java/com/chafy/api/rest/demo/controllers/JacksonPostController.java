package com.chafy.api.rest.demo.controllers;

import com.chafy.api.rest.demo.dto.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/jackson_posts")
public class JacksonPostController {
    /*
        스프링 DI를 통해 컨트롤러에서 Jackson ObjectMapper를 얻는다.
        스프링이 등록된 객체(Bean)를 관리한다.
        생성자에 명시하면 받아서 사용할 수 있다. 사용 = 의존성/의존관계를 주입 받는다 (Dependency Injection)
     */
    private final ObjectMapper objectMapper;

    public JacksonPostController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    ArrayList<PostDto> postDtos = new ArrayList<>(List.of(
            new PostDto("123", "제목", "테스트입니다."),
            new PostDto("456", "제목2", "2번테스트입니다.")
    ));

    // jackson을 이용한 게시물 목록 구현하는 코드
    @GetMapping
    public List<PostDto> list() {
        return postDtos;
    }

    // ObjectMapper를 이용한 게시물 상세
    @GetMapping("/jackson/{id}")
    public String detail(@PathVariable("id") String id) throws JacksonException {
        PostDto postDto = new PostDto(id, "제목", "내용이다.");

        /*
            DTO를 JSON 문자열 데이터로 변환한다.(직렬화)
            직렬화를 위한 메서드는 여러가지가 있는데...
            writeValueAsString()은 기능적으로 writeValue()와 동일하지만 매개변수가 하나밖에 들어가지 않아 더 효율적이다.
         */
        return objectMapper.writeValueAsString(postDto); // 스프링에 Jackson ObjectMapper가 내장되어 있어 바로 사용가능하다.
    }

    /*
        게시물 상세
        스프링에 내장된 Jackson을 바로 사용할 수 있다.
     */
    @GetMapping("/{id}")
    public PostDto detail2(@PathVariable("id") String id) {
        PostDto postDto = new PostDto(id, "제목", "테스트입니다.");

        /*
            detail()에서 writeValueAsString()을 사용하지 않고
            그냥 메서드 타입을 PostDto로 했을 뿐인데 자동으로 JSON 데이터로 출력한다.
            DTO, 즉 getter와 setter가 있는 클래스를 반환할 때는 자동으로 Jackson ObjectMapper를 통해 JSON 데이터를 반환하나보다.
         */
        return postDto;
    }

    // Jackson을 이용한 게시물 생성
    @PostMapping("/jackson")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create2(@RequestBody(required = false) String body) throws JacksonException {
        PostDto postDto = objectMapper.readValue(body, PostDto.class); // readValue : 지정된 JSON 콘텐츠 문자열에서 JSON 콘텐츠를 역직렬화하는 방법
        return postDto;
    }

    // 스프링에 내장된 Jackson을 이용한 게시물 생성
    // ObjectMapper의 readValue()를 생략해도 알아서 스프링이 해결해준다.
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody(required = false) PostDto postDto) {
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
