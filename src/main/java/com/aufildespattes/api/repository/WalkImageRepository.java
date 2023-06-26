package com.aufildespattes.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aufildespattes.api.entity.WalkImage;

@Repository
public interface WalkImageRepository extends CrudRepository<WalkImage, Long> {
    
    WalkImage getWalkImageByName(String name);
}
