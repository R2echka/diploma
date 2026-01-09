package com.mew.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mew.diploma.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
