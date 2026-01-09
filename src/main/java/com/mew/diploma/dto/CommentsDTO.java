package com.mew.diploma.dto;

import java.util.List;

import com.mew.diploma.model.Comment;

import lombok.Data;

@Data
public class CommentsDTO {
    
    private Integer count;
    private List<CommentDTO> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentDTO> getResults() {
        return results;
    }

    public void setResults(List<CommentDTO> results) {
        this.results = results;
    }
}
