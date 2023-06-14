package com.example.toad.service;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import com.example.toad.repo.GeoMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public GeoMark saveGeoMark(GeoMark geoMark){
        return geoMarkRepository.save(geoMark);
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

    public Page<GeoMark> findPaginated(Pageable pageable, List<GeoMark> geoMarks, UserEntity user){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GeoMark> list;

        if(geoMarks.size() < startItem){
            list = Collections.emptyList();
        }
        else{
            int toIndex = Math.min(startItem + pageSize, geoMarks.size());
            list = geoMarks.subList(startItem, toIndex);
        }

        Pageable pageable1 = PageRequest.of(currentPage,pageSize);

        Page<GeoMark> geoMarkPage
                = geoMarkRepository.findByUser(pageable1, user);

        return geoMarkPage;
    }
}
