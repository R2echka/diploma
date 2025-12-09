package com.mew.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mew.diploma.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    
}
