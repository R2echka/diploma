package com.mew.diploma.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ad")
public class Ad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ad_description")
    private String description;
    
    @Column(name = "price")
    private Integer price;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "image_id")
    private Long image;
    
    @Column(name = "author_id")
    private Long authorId;

    public Ad() {
    }
    
    public Ad(String description, Integer price, String title) {
        this.description = description;
        this.price = price;
        this.title = title;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}