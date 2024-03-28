package com.chafy.api.rest.demo.daos;

import com.chafy.api.rest.demo.dto.*;

import java.util.*;

public interface PostDAO {

    List<PostDto> findAll();

    PostDto find(String id);

    void save(PostDto postDto);

    void delete(String id);
}
