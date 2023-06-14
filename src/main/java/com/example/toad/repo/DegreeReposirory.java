package com.example.toad.repo;

import com.example.toad.models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeReposirory extends JpaRepository<Degree, Long> {

    Degree findByName (String name);
}
