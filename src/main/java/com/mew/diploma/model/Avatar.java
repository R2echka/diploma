package com.mew.diploma.model;

import javax.persistence.GeneratedValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "avatar")
public class Avatar {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "user_id")
    private long user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }
}
