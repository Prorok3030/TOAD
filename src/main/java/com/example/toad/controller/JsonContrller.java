package com.example.toad.controller;

import com.example.toad.models.GeoMark;
import com.example.toad.service.GeoMarkService;
import com.example.toad.service.ResponceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public  class JsonContrller {

    @Autowired
    public JsonContrller(GeoMarkService geoMarkService){
        this.geoMarkService = geoMarkService;
    }
    private final GeoMarkService geoMarkService;

    @GetMapping("/getMarks")
    public List<GeoMark> getAllMarks(){
        List<GeoMark> geoMarksList = geoMarkService.findAll();
        return geoMarksList;
    }

    //Возможно удалить
    @GetMapping("/getAllMarks")
    public ResponseEntity<Object> Get(){
        try{
            List<GeoMark> result = geoMarkService.findAll();
            return ResponceHandler.generateResponce(HttpStatus.OK,result);
        }
        catch (Exception e){
            return ResponceHandler.generateResponce(HttpStatus.MULTI_STATUS,null);
        }
    }
}