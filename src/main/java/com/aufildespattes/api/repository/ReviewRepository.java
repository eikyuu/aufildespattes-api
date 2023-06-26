package com.aufildespattes.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aufildespattes.api.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    
}
