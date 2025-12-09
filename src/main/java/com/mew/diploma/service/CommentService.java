package com.mew.diploma.service;

import com.mew.diploma.dto.Comments;
import com.mew.diploma.model.Comment;

public interface CommentService {
    
    Comments getAdComments(long id);
    Comment newComment(long id, String text);
    void deleteComment(long id);
    Comment editComment(long id, String text);
}
