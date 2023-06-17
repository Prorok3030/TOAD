package com.example.toad.controller;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import com.example.toad.service.GeoMarkService;
import com.example.toad.service.ResponceHandler;
import com.example.toad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public  class JsonContrller {

    @Autowired
    public JsonContrller(GeoMarkService geoMarkService, UserService userService){
        this.geoMarkService = geoMarkService;
        this.userService = userService;
    }
    private final GeoMarkService geoMarkService;
    private final UserService userService;

    @GetMapping("/getMarks")
    public List<GeoMark> getAllMarks(){
        List<GeoMark> geoMarksList = geoMarkService.findAll();
        return geoMarksList;
    }
    @GetMapping("/getUserMarks")
    public List<GeoMark> getUserMarks(Principal principal){
        UserEntity user = userService.findByUsername(principal.getName());
        List<GeoMark> userGeoMarksList = geoMarkService.findByUser(user);
        return userGeoMarksList;
    }
    @GetMapping("/getUserFav")
    public List<GeoMark> getUserFav(Principal principal){
        UserEntity user = userService.findByUsername(principal.getName());
        List<GeoMark> userGeoMarksList = user.getGeoMarks();
        return userGeoMarksList;
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