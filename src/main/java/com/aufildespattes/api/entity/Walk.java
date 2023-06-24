package com.aufildespattes.api.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.aufildespattes.api.constant.ObligatoryLeash;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "walk")
public class Walk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="walk_name", nullable=false)
    private String name;

    private String slug;
    
    @NotNull
    private String description;
    
    @NotNull
    private String city;

    @NotNull
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    private String street;

    private Double latitude;

    private Double longitude;

    @NotNull
    @Column(name = "obligatory_leash")
    private ObligatoryLeash obligatoryLeash;

    @NotNull
    @Column(name = "water_point")
    private boolean waterPoint;

    @NotNull
    @Column(name = "processionary_caterpillar_alert")
    private boolean processionaryCaterpillarAlert;

    @NotNull
    @Column(name = "cyanobacteria_alert")
    private boolean cyanobacteria_alert;

    @NotNull
    private Integer note;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}
