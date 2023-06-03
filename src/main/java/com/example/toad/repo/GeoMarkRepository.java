package com.example.toad.repo;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeoMarkRepository extends JpaRepository<GeoMark, Long> {

    List<GeoMark> findByUser(UserEntity user);
}
