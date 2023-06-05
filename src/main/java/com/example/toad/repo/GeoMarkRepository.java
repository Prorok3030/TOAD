package com.example.toad.repo;

import com.example.toad.models.GeoMark;
import com.example.toad.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeoMarkRepository extends JpaRepository<GeoMark, Long> {

    @Override
    Optional<GeoMark> findById(Long aLong);

    List<GeoMark> findByUser(UserEntity user);
    Page<GeoMark> findByUser(Pageable pageable, UserEntity user);
}
