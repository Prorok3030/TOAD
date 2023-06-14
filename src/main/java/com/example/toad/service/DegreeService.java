package com.example.toad.service;

import com.example.toad.models.Category;
import com.example.toad.models.Degree;
import com.example.toad.repo.DegreeReposirory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeService {

    @Autowired
    public DegreeService(DegreeReposirory degreeReposirory) {
        this.degreeReposirory = degreeReposirory;
    }

    private final DegreeReposirory degreeReposirory;

    public List<Degree> findAll(){
        return degreeReposirory.findAll();
    }

}
