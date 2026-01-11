package com.mew.diploma.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;

public interface AdService {

    AdsDTO getAllAds();
    AdDTO newAd(UpdateAdDTO ad, MultipartFile image);
    AdInfoDTO getAd(long id);
    ResponseEntity<?> deleteAd(long id, String email);
    ResponseEntity<?> changeAd(long id, UpdateAdDTO updateAd, String email);
    AdsDTO getUsersAds(String email);
    ResponseEntity<?> changeAdImage(long id, MultipartFile image, String email);
}
