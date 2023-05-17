package com.example.toad.repo;

import com.example.toad.models.GeoMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoMarkRepository extends JpaRepository<GeoMark, Long> {
}
