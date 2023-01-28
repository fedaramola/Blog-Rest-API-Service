package com.demotek.blog.controller;

import com.demotek.blog.dto.CommentDto;
import com.demotek.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(name = "postId") long postId, @RequestBody CommentDto commentDto) {
        System.out.println(postId);
        System.out.println(commentDto);
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable(name = "postId") long postId) {

        return commentService.getCommentByPostId(postId);
    }

    @GetMapping("posts/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable(name = "postId") long postId, @PathVariable(name = "commentId") long commentId) {
        return commentService.getCommentById(postId, commentId);
    }

    @PutMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "postId") long postId,
                                                    @PathVariable(name = "commentId") long commentId,
                                                    @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")

    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") long postId, @PathVariable(name = "commentId") long commentId) {

        commentService.deleteComment(postId, commentId);

        return new ResponseEntity("Deleted successfully ", HttpStatus.OK);
    }
}
