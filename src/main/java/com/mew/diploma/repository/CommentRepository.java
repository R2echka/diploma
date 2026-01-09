package com.mew.diploma.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mew.diploma.model.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAd(long adId);
    Comment findById(long id);
}
