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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public ObligatoryLeash getObligatoryLeash() {
		return obligatoryLeash;
	}

	public void setObligatoryLeash(ObligatoryLeash obligatoryLeash) {
		this.obligatoryLeash = obligatoryLeash;
	}

	public boolean isWaterPoint() {
		return waterPoint;
	}

	public void setWaterPoint(boolean waterPoint) {
		this.waterPoint = waterPoint;
	}

	public boolean isProcessionaryCaterpillarAlert() {
		return processionaryCaterpillarAlert;
	}

	public void setProcessionaryCaterpillarAlert(boolean processionaryCaterpillarAlert) {
		this.processionaryCaterpillarAlert = processionaryCaterpillarAlert;
	}

	public boolean isCyanobacteria_alert() {
		return cyanobacteria_alert;
	}

	public void setCyanobacteria_alert(boolean cyanobacteria_alert) {
		this.cyanobacteria_alert = cyanobacteria_alert;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
