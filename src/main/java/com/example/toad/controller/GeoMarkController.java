package com.example.toad.controller;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import com.example.toad.service.GeoMarkService;
import com.example.toad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GeoMarkController {

    @Autowired
    public GeoMarkController(GeoMarkService geoMarkService, UserService userService){
        this.geoMarkService = geoMarkService;
        this.userService = userService;
    }
    private final GeoMarkService geoMarkService;
    private final UserService userService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/geoMarks")
    public String findAll(Model model){
        List<GeoMark> geoMarks = geoMarkService.findAll();
        model.addAttribute("geoMarks",geoMarks);
        return "geoMarks";
    }

    @GetMapping("/geoMarkAdd")
    public String createGeoMarkForm(GeoMark geoMark){
        return "geoMarkAdd";
    }

    @PostMapping("/geoMarkAdd")
    public String createTest(Principal principal, GeoMark geoMark){
        UserEntity user = userService.findByUsername(principal.getName());
        geoMark.setUser(user);
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

    @GetMapping("/myGeoMarks")
    public String userGeoMarks(Principal principal, Model model){
        UserEntity user = userService.findByUsername(principal.getName());
        List<GeoMark> geoMarks = geoMarkService.findByUser(user);
        model.addAttribute("userGeoMarks",geoMarks);
        return "/userGeoMarks";
    }
}
