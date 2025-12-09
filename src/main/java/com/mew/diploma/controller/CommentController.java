package com.mew.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.dto.Comments;
import com.mew.diploma.model.Comment;
import com.mew.diploma.service.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(value = "http://localhost:3000")
@RestController("/ads")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    
    @GetMapping("/{adId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Comments getAdComments(@PathVariable long adId, @RequestBody long id) {
        return commentService.getAdComments(id);
    }

    @PostMapping("/{adId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Comment newComment(@PathVariable long adId, @RequestBody long id, String text) {
        return commentService.newComment(id, text);
    }

    @DeleteMapping("/{adId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable long adId, @PathVariable long id) {
        commentService.deleteComment(id);
    }

    @PatchMapping("/{adId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment editComment(@PathVariable long adId, @PathVariable long id, @RequestBody String text){
        return commentService.editComment(id, text);
    }
}
