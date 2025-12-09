package com.mew.diploma.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mew.diploma.dto.Ads;
import com.mew.diploma.model.Ad;

@Mapper(componentModel = "spring")
public class AdMapper {

    public Ads toAds(List<Ad> adList) {
        Ads ads = new Ads();
        ads.setResults(adList);
        ads.setCount(adList.size());
        return ads;
    }
}
