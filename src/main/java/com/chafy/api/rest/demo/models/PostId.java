package com.chafy.api.rest.demo.models;

import com.github.f4b6a3.tsid.*;

import java.util.*;

public class PostId {
    private String id;

    public PostId(String id) {
        this.id = id;
    }

    // Tsid를 이용하여 id 자동 생성
    public static PostId generate() {
        return new PostId(TsidCreator.getTsid().toString());
    }

    public static PostId of(String id) { // 매개변수로 들어오는 id를 가지고 PostId를 생성한다.
        return new PostId(id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId = (PostId) o;
        return Objects.equals(id, postId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
