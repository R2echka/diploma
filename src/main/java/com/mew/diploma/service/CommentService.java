package com.mew.diploma.service;

import com.mew.diploma.dto.CommentDTO;
import com.mew.diploma.dto.CommentsDTO;
import com.mew.diploma.model.Comment;

public interface CommentService {
    
    CommentsDTO getAdComments(long id);
    CommentDTO newComment(String text);
    void deleteComment(long id);
    CommentDTO editComment(long id, String text);
}
