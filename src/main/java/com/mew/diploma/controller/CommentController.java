package com.mew.diploma.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.dto.CommentDTO;
import com.mew.diploma.dto.CommentsDTO;
import com.mew.diploma.service.CommentService;


@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    
    @GetMapping("{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentsDTO getAdComments(@PathVariable long id) {
        return commentService.getAdComments(id);
    }

    @PostMapping("{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public CommentDTO newComment(@PathVariable long id, @RequestBody Map<String, String> text, Authentication authentication) {
        return commentService.newComment(id, text.get("text"), authentication.getName());
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable long adId, @PathVariable("commentId") long id, Authentication authentication) {
        return commentService.deleteComment(id, authentication.getName());
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable long adId, @PathVariable("commentId") long id, @RequestBody String text, Authentication authentication){
        return commentService.editComment(id, text, authentication.getName());
    }
}
