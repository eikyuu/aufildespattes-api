package com.aufildespattes.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aufildespattes.api.entity.Walk;
import com.aufildespattes.api.repository.WalkRepository;

import lombok.Data;

@Data
@Service
public class WalkService {
    
    @Autowired
    private WalkRepository walkRepository;

    public Optional<Walk> getWalk(final long id) {
        return walkRepository.findById(id);
    }

    public Iterable<Walk> getWalks() {
        return walkRepository.findAll();
    }

    public void deleteWalk(final Long id) {
        walkRepository.deleteById(id);
    }

    public Walk saveWalk(Walk walk) {
       return walkRepository.save(walk);
    }

    public Walk getWalkBySlug(String slug) {
        return walkRepository.findBySlug(slug);
    }
    

}
