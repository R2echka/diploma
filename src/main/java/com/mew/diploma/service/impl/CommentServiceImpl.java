package com.mew.diploma.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.mew.diploma.dto.CommentDTO;
import com.mew.diploma.dto.CommentsDTO;
import com.mew.diploma.mapper.CommentMapper;
import com.mew.diploma.model.Comment;
import com.mew.diploma.repository.CommentRepository;
import com.mew.diploma.service.CommentService;
import com.mew.diploma.service.UserService;

@Service
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
    public CommentsDTO getAdComments(long id) {
        return mapper.toComments(commentRepository.findByAd(id));
    }

    @Override
    public CommentDTO newComment(long id, String text, String email) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setCreatedAt(Instant.now().toEpochMilli());
        comment.setAuthorId(userService.getUser(email).getId());
        comment.setAd(id);
        commentRepository.save(comment);
        return mapper.toDTO(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO editComment(long id, String text) {
        Comment comment = commentRepository.findById(id);
        comment.setText(text);
        commentRepository.save(comment);
        return mapper.toDTO(comment);
    }
    
}
