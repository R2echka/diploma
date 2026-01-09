package com.mew.diploma.service.impl;

import com.mew.diploma.dto.Comments;
import com.mew.diploma.mapper.CommentMapper;
import com.mew.diploma.model.Comment;
import com.mew.diploma.repository.CommentRepository;
import com.mew.diploma.service.CommentService;
import com.mew.diploma.service.UserService;

public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapper mapper;
    UserService userService;


    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper mapper, UserService userService){
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public Comments getAdComments(long id) {
        return mapper.toComments(commentRepository.findByAd(id));
    }

    @Override
    public Comment newComment(long id, String text) {
        Comment comment = new Comment();
        comment.setAd(id);
        comment.setText(text);
        comment.setAuthor(userService.getUser().getId());
        commentRepository.save(comment);
        return comment;
        
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment editComment(long id, String text) {
        Comment comment = commentRepository.findById(id);
        comment.setText(text);
        commentRepository.save(comment);
        return comment;
    }
    
}
