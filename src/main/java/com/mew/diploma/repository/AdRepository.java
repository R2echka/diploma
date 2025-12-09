package com.mew.diploma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mew.diploma.model.Ad;
import java.util.List;


public interface AdRepository extends JpaRepository<Ad, Long> {
    
    Ad findById(long id);
    Ad deleteById(long id);
    List<Ad> findByAuthor(long author);
}
