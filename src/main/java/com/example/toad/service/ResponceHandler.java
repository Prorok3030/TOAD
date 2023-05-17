package com.example.toad.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponceHandler {
    public static ResponseEntity<Object> generateResponce(HttpStatus status, Object responceObj){
        Map <String, Object> map = new HashMap<String, Object>();
            map.put("type", "FeatureCollection");
            map.put("features", responceObj);

            return new ResponseEntity<Object>(map,status);
    }
}
