package com.mew.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mew.diploma.dto.Ads;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;
import com.mew.diploma.service.AdService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(value = "http://localhost:3000")
@RestController("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService){
        this.adService = adService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ad newAd(@RequestBody Ad ad, Image image) {
        return adService.newAd(ad, image);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ad getAd(@PathVariable long id) {
        return adService.getAd(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable long id){
        adService.deleteAd(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeAd(@PathVariable long id, @RequestBody String title, Integer price, String description){
        adService.changeAd(id, title, price, description);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public Ads getUsersAds() {
        return adService.getUsersAds();
    }

    @PatchMapping("/{id}/image")
    @ResponseStatus(HttpStatus.OK)
    public String changeAdImage(@PathVariable long id, @RequestBody Image image){
        return adService.changeAdImage(id, image);
    }
}
