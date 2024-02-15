package com.chafy.api.rest.demo.controllers;

import com.chafy.api.rest.demo.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/content_posts")

public class ContentPostController {

    // 게시물 목록
    @GetMapping("/")
    public String list() {
        return "게시물 목록\n";
    }


    // 게시물 상세
    @GetMapping("/{post_id}")
    // @PathVariable은 @GetMapping의 중괄호{}에 있는 값을 변수로 사용할 수 있다.
    public String detail(@PathVariable("post_id") String id) {
        if (id.equals("404")) {
            throw new PostNotFound();
        }
        return id + "의 게시물 상세";
    }

    // 게시물 생성
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody(required = false) String body) {
        return "게시물 생성 : " + body + "\n";
    }

    // 게시물 수정
//    @PatchMapping("/{post_id}")
    @PutMapping("/{post_id}")
    public String update(@PathVariable("post_id") String id) {
        return "게시물 수정 : " + id + "\n";
    }

    // 게시물 삭제
    @DeleteMapping("/{post_id}")
    public String delete(@PathVariable("post_id") String id) {
        return "게시물 삭제 : " + id + "\n";
    }

    @ExceptionHandler(PostNotFound.class) // 예외 처리를 따로 해주고 싶을 때
    @ResponseStatus(HttpStatus.NOT_FOUND) // @ResponseStatus를 쓰지 않으면 기본적으로 200이 날라감
    public String postNotFound() {
        return "게시물을 찾을 수 없습니다.";
    }
}
