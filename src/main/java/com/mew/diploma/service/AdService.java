package com.mew.diploma.service;

import com.mew.diploma.dto.Ads;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;

public interface AdService {

    Ads getAllAds();
    Ad newAd(Ad ad, Image image);
    Ad getAd(long id);
    void deleteAd(long id);
    Ad changeAd(long id, String title, Integer price, String description);
    Ads getUsersAds();
    String changeAdImage(long id, Image image);
}
