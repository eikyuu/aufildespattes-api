package com.aufildespattes.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aufildespattes.api.entity.Walk;

@Repository
public interface WalkRepository extends CrudRepository<Walk, Long> {
    
}
