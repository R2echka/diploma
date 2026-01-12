package com.mew.diploma.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;
import com.mew.diploma.mapper.AdMapper;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;
import com.mew.diploma.model.Role;
import com.mew.diploma.model.User;
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
    public ResponseEntity<?> deleteAd(long id, String email) {
        User user = userService.getUser(email);
        if (user.getRole().equals(Role.ADMIN) || adRepository.findAuthorIdById(id).equals(user.getId())) {
            adRepository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else{
            return ResponseEntity.status(403).build();
        }
    }

    @Override
    public ResponseEntity<?> changeAd(long id, UpdateAdDTO updateAd, String email) {
        User user = userService.getUser(email);
        if (user.getRole().equals(Role.ADMIN) || adRepository.findAuthorIdById(id).equals(user.getId())) {
            Ad ad = adRepository.findById(id);
            ad.setTitle(updateAd.getTitle());
            ad.setPrice(updateAd.getPrice());
            ad.setDescription(updateAd.getDescription());
            adRepository.save(ad);
            return ResponseEntity.status(200).body(mapper.toDTO(ad));
        } else{
            return ResponseEntity.status(403).build();
        }
    }

    @Override
    public AdsDTO getUsersAds(String email) {
        return mapper.toAds(adRepository.findByAuthorId(userService.getUser(email).getId()));
    }

    @Override
    public ResponseEntity<?> changeAdImage(long id, MultipartFile file, String email) {
        User user = userService.getUser(email);
        if (user.getRole().equals(Role.ADMIN) || adRepository.findAuthorIdById(id).equals(user.getId())) {
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
        return ResponseEntity.status(200).body(mapper.toDTO(ad).getImage());
        } else{
            return ResponseEntity.status(403).build();
        }
    }
}
