package com.example.toad.models;
import jakarta.persistence.*;

import java.awt.*;

@Entity
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "locationpoint", columnDefinition = "POINT")
    private Point locationpoint;

    public PointEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getLocationpoint() {
        return locationpoint;
    }

    public void setLocationpoint(Point locationpoint) {
        this.locationpoint = locationpoint;
    }
}
