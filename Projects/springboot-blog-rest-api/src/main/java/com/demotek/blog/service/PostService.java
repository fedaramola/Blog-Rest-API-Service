package com.demotek.blog.service;

import com.demotek.blog.dto.PostDto;
import com.demotek.blog.dto.PostResponse;



public interface PostService {
    PostDto creatPost(PostDto postDto);

   PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deleteById(long id);
}
