package com.demotek.blog.controller;

import com.demotek.blog.dto.PostDto;
import com.demotek.blog.dto.PostResponse;
import com.demotek.blog.service.PostService;
import com.demotek.blog.utils.AppCostants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/posts")
public class PostController {


    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;

    //create post
    @RequestMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.creatPost(postDto), HttpStatus.CREATED);
    }

    //retrieve all post
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppCostants.DEFAULT_PAGE_NUMBER, required = false ) int pageNo,
            @RequestParam(value ="pageSize", defaultValue = AppCostants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppCostants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppCostants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo,pageSize,sortBy, sortDir);
    }

    //retrieve all post by  id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id) {

        return new ResponseEntity<>(postService.updatePostById(postDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id) {

        return new ResponseEntity<>("post successfully Deleted", HttpStatus.OK);

    }
}
