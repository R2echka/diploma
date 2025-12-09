package com.mew.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    
    @Column(name="author")
    private long author;
    @Column(name="created_at")
    private int createdAt;
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;
    @Column(name="comment_text")
    private String text;
    @Column(name="comment_ad")
    private long ad;

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getAd() {
        return ad;
    }

    public void setAd(long ad) {
        this.ad = ad;
    }
}
