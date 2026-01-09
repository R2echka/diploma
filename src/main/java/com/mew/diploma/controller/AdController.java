package com.mew.diploma.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;
import com.mew.diploma.model.Image;
import com.mew.diploma.service.AdService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService){
        this.adService = adService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AdsDTO getAllAds() {
        return adService.getAllAds();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AdDTO newAd(@RequestPart("properties") UpdateAdDTO properties, @RequestPart("image") MultipartFile image) {
        return adService.newAd(properties, image);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdInfoDTO getAd(@PathVariable long id) {
        return adService.getAd(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAd(@PathVariable long id){
        adService.deleteAd(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeAd(@PathVariable long id, @RequestBody UpdateAdDTO updateAd){
        adService.changeAd(id, updateAd);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public AdsDTO getUsersAds(Authentication authentication) {
        return adService.getUsersAds(authentication.getName());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String changeAdImage(@PathVariable long id, @RequestBody MultipartFile image){
        return adService.changeAdImage(id, image);
    }
}
