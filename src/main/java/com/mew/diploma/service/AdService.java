package com.mew.diploma.service;

import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;

public interface AdService {

    AdsDTO getAllAds();
    AdDTO newAd(UpdateAdDTO ad, MultipartFile image);
    AdInfoDTO getAd(long id);
    void deleteAd(long id);
    AdDTO changeAd(long id, UpdateAdDTO updateAd);
    AdsDTO getUsersAds(String email);
    String changeAdImage(long id, MultipartFile image);
}
