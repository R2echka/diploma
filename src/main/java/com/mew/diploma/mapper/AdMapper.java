package com.mew.diploma.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mew.diploma.dto.AdDTO;
import com.mew.diploma.dto.AdInfoDTO;
import com.mew.diploma.dto.AdsDTO;
import com.mew.diploma.dto.UpdateAdDTO;
import com.mew.diploma.model.Ad;
import com.mew.diploma.model.Image;
import com.mew.diploma.model.User;
import com.mew.diploma.repository.ImageRepository;
import com.mew.diploma.repository.UserRepository;

@Component
public class AdMapper {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    
    public AdMapper(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public AdsDTO toAds(List<Ad> adList) {
        List<AdDTO> results = new ArrayList<>();
    
        for (Ad ad : adList) {
            results.add(toDTO(ad));
        }

        AdsDTO ads = new AdsDTO();
        ads.setResults(results);
        ads.setCount(adList.size());
        return ads;
    }

    public AdDTO toDTO(Ad ad){
        AdDTO adDTO = new AdDTO();
        adDTO.setAuthor(ad.getAuthorId());
        adDTO.setPk(ad.getId());
        adDTO.setPrice(ad.getPrice());
        adDTO.setTitle(ad.getTitle());

        if (ad.getImage() != null) {
            Image image = imageRepository.findById((long)ad.getImage());
            String base64Image = java.util.Base64.getEncoder()
                    .encodeToString(image.getData());
            String mediaType = image.getMediaType();
            adDTO.setImage("data:" + mediaType + ";base64," + base64Image);
        }

        return adDTO;
    }

    public Ad fromDTO(AdDTO dto){
        Ad ad = new Ad();
        ad.setAuthorId(dto.getAuthor());
        ad.setId(dto.getPk());
        ad.setPrice(dto.getPrice());
        ad.setTitle(dto.getTitle());

        return ad;
    }

    public AdInfoDTO toExtended(Ad ad){
        User author = userRepository.findById((long)ad.getAuthorId());

        AdInfoDTO adInfo = new AdInfoDTO();
        adInfo.setPk(ad.getId());
        adInfo.setPrice(ad.getPrice());
        adInfo.setTitle(ad.getTitle());
        adInfo.setDescription(ad.getDescription());
        adInfo.setAuthorFirstName(author.getFirstName());
        adInfo.setAuthorLastName(author.getLastName());
        adInfo.setEmail(author.getEmail());
        adInfo.setPhone(author.getPhone());

        if (ad.getImage() != null) {
            Image image = imageRepository.findById((long)ad.getImage());
            String base64Image = java.util.Base64.getEncoder()
                    .encodeToString(image.getData());
            String mediaType = image.getMediaType();
            adInfo.setImage("data:" + mediaType + ";base64," + base64Image);
        }
        return adInfo;
    }

    public Ad newAd(UpdateAdDTO updateAd){
        
        Ad newAd = new Ad();
        newAd.setTitle(updateAd.getTitle());
        newAd.setPrice(updateAd.getPrice());
        newAd.setDescription(updateAd.getDescription());

        return newAd;

    }
}
