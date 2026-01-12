package com.mew.diploma.service;

import org.springframework.http.ResponseEntity;

import com.mew.diploma.dto.CommentDTO;
import com.mew.diploma.dto.CommentsDTO;

public interface CommentService {
    
    CommentsDTO getAdComments(long id);
    CommentDTO newComment(long id, String text, String email);
    ResponseEntity<?> deleteComment(long id, String email);
    ResponseEntity<?> editComment(long id, String text, String email);
}
