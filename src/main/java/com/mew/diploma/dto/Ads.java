package com.mew.diploma.dto;

import java.util.List;

import com.mew.diploma.model.Ad;

import lombok.Data;

@Data
public class Ads {
    
    private Integer count;
    private List<Ad> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Ad> getResults() {
        return results;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }
}
