package com.mew.diploma.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "comment")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Long createdAt;
    
    @Column(name = "comment_text", length = 1000)
    private String text;
    
    @Column(name = "comment_ad")
    private Long ad;

    @Column(name = "author_id")
    private Long authorId;
    
    public Comment() {
    }
    
    public Comment(String text, Long ad) {
        this.text = text;
        this.ad = ad;
        this.createdAt = Instant.now().toEpochMilli();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAd() {
        return ad;
    }

    public void setAd(Long ad) {
        this.ad = ad;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}