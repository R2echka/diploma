package com.mew.diploma.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ad")
public class Ad {

    public Ad(long author, String description, int price, String title){
        this.author = author;
        this.description = description;
        this.price = price;
        this.title = title;
    }
    
    @Column(name="author")
    private long author;
    @Column(name="ad_description")
    private String description;
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;
    @Column(name="price")
    private int price;
    @Column(name="title")
    private String title;
    @Column(name="image_id")
    private String image;

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
