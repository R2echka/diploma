package com.mew.diploma.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;
import com.mew.diploma.mapper.AdMapper;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;
import com.mew.diploma.repository.AdRepository;
import com.mew.diploma.repository.ImageRepository;
import com.mew.diploma.service.AdService;
import com.mew.diploma.service.UserService;

@Service
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
    public AdsDTO getAllAds() {
        return mapper.toAds(adRepository.findAll());
    }

    @Override
    public AdDTO newAd(UpdateAdDTO adDTO, MultipartFile file) {
        Image image = new Image();
        try {
                image.setData(file.getBytes());
                image.setMediaType(file.getContentType());
                image.setFilePath(file.getOriginalFilename());
            } catch (Exception e) {
            }
        Image savedImage = imageRepository.save(image);
        Ad ad = mapper.newAd(adDTO);
        ad.setImage(savedImage.getId());
        adRepository.save(ad);
        return mapper.toDTO(ad);
    }

    @Override
        public AdInfoDTO getAd(long id) {
            return mapper.toExtended(adRepository.findById(id));
        }

    @Override
    public void deleteAd(long id) {
        adRepository.deleteById(id);
    }

    @Override
    public AdDTO changeAd(long id, UpdateAdDTO updateAd) {
        Ad ad = adRepository.findById(id);
        ad.setTitle(updateAd.getTitle());
        ad.setPrice(updateAd.getPrice());
        ad.setDescription(updateAd.getDescription());
        adRepository.save(ad);
        return mapper.toDTO(ad);
    }

    @Override
    public AdsDTO getUsersAds(String email) {
        return mapper.toAds(adRepository.findByAuthorId(userService.getUser(email).getId()));
    }

    @Override
    public String changeAdImage(long id, MultipartFile file) {
        Ad ad = adRepository.findById(id);
        Image image = new Image();
        try {
                image.setData(file.getBytes());
                image.setMediaType(file.getContentType());
                image.setFilePath(file.getOriginalFilename());
            } catch (Exception e) {
            }
        Image savedImage = imageRepository.save(image);
        ad.setImage(savedImage.getId());
        adRepository.save(ad);
        return mapper.toDTO(ad).getImage();
    }
}
