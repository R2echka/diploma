package com.mew.diploma.service.impl;

import com.mew.diploma.dto.Ads;
import com.mew.diploma.mapper.AdMapper;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;
import com.mew.diploma.repository.AdRepository;
import com.mew.diploma.repository.ImageRepository;
import com.mew.diploma.repository.UserRepository;
import com.mew.diploma.service.AdService;
import com.mew.diploma.service.UserService;

public class AdServiceImpl implements AdService {
    AdRepository adRepository;
    AdMapper mapper;
    ImageRepository imageRepository;
    UserService userService;


    public AdServiceImpl(AdRepository adRepository, AdMapper mapper, ImageRepository imageRepository, UserService userService){
        this.adRepository = adRepository;
        this.mapper = mapper;
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    @Override
    public Ads getAllAds() {
        return mapper.toAds(adRepository.findAll());
    }

    @Override
    public Ad newAd(Ad ad, Image image) {
        imageRepository.save(image);
        ad.setImage(image.getId().toString());
        ad.setAuthor(userService.getUser().getId());
        adRepository.save(ad);
        return ad;
    }

    @Override
        public Ad getAd(long id) {
            return adRepository.findById(id);
        }

    @Override
    public void deleteAd(long id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ad changeAd(long id, String title, Integer price, String description) {
        Ad ad = adRepository.findById(id);
        ad.setTitle(title);
        ad.setPrice(price);
        ad.setDescription(description);
        adRepository.save(ad);
        return ad;
    }

    @Override
    public Ads getUsersAds() {
        return mapper.toAds(adRepository.findByAuthor(userService.getUser().getId()));
    }

    @Override
    public String changeAdImage(long id, Image image) {
        Ad ad = adRepository.findById(id);
        ad.setImage(image.getId().toString());
        return ad.getImage();
    }
    
}
