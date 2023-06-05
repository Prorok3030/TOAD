package com.example.toad.controller;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import com.example.toad.repo.GeoMarkRepository;
import com.example.toad.service.GeoMarkService;
import com.example.toad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class GeoMarkController {

    @Autowired
    public GeoMarkController(GeoMarkService geoMarkService, UserService userService, GeoMarkRepository geoMarkRepository){
        this.geoMarkService = geoMarkService;
        this.userService = userService;
        this.geoMarkRepository = geoMarkRepository;
    }
    private final GeoMarkService geoMarkService;
    private final UserService userService;
    private final GeoMarkRepository geoMarkRepository;

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

    @GetMapping("/userGeoMarks")
    public String userGeoMarks(Principal principal, Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size){
        UserEntity user = userService.findByUsername(principal.getName());
        List<GeoMark> geoMarks = geoMarkService.findByUser(user);
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<GeoMark> geoMarkPage = geoMarkService.findPaginated(PageRequest.of(currentPage -1, pageSize),geoMarks, user);
        model.addAttribute("geoMarkPage", geoMarkPage);
        int totalPages = geoMarkPage.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", totalPages);
        }

        return "/userGeoMarks";
    }

    @GetMapping("/geoMarkAddFav/{id}")
    public String addFav (@PathVariable("id") Long id, Principal principal){
        UserEntity user = userService.findByUsername(principal.getName());
        GeoMark geoMark = geoMarkRepository.findById(id).get();
        user.getGeoMarks().add(geoMark);
        userService.save(user);
        return "redirect:/geoMarks";
    }
}
