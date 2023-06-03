package com.example.toad.service;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import com.example.toad.repo.GeoMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoMarkService {

    @Autowired
    public GeoMarkService(GeoMarkRepository geoMarkRepository) {
        this.geoMarkRepository = geoMarkRepository;
    }
    private final GeoMarkRepository geoMarkRepository;

    public List<GeoMark> findAll(){
        return geoMarkRepository.findAll();
    }

    public GeoMark saveGeoMark(GeoMark test){
        return geoMarkRepository.save(test);
    }

    public void deleteById(Long id){
        geoMarkRepository.deleteById(id);
    }

    public GeoMark findById(Long id) {
        return geoMarkRepository.findById(id).orElse(null);
    }

    public List<GeoMark> findByUser(UserEntity user){
       return geoMarkRepository.findByUser(user);
    }
}
