package com.example.toad.controller;

import com.example.toad.models.GeoMark;
import com.example.toad.service.GeoMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GeoMarkController {

    @Autowired
    public GeoMarkController(GeoMarkService geoMarkService){
        this.geoMarkService = geoMarkService;
    }
    private final GeoMarkService geoMarkService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/geoMarks")
    public String findAll( Model model){
        List<GeoMark> geoMarks = geoMarkService.findAll();
        model.addAttribute("geoMarks",geoMarks);
        return "geoMarks";
    }

    @GetMapping("/geoMarkAdd")
    public String createGeoMarkForm(GeoMark geoMark){
        return "geoMarkAdd";
    }

    @PostMapping("/geoMarkAdd")
    public String createTest(GeoMark geoMark){
        geoMarkService.saveGeoMark(geoMark);
        return "redirect:/geoMarks";
    }

    @GetMapping("/geoMarkEdit/{id}")
    public String editGeoMarkForm(@PathVariable("id") Long id, Model model){
        GeoMark geoMark = geoMarkService.findById(id);
        model.addAttribute("geoMark",geoMark);
        return "/geoMarkEdit";
    }

    @PostMapping("/geoMarkEdit")
    public String editGeoMark(GeoMark geoMark){
        geoMarkService.saveGeoMark(geoMark);
        return "redirect:/geoMarks";
    }

    @GetMapping("/geoMarkDelete/{id}")
    public String deleteGeoMark(@PathVariable("id") Long id){
        geoMarkService.deleteById(id);
        return "redirect:/geoMarks";
    }
}
