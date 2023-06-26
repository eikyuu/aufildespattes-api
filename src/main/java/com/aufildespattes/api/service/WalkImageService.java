package com.aufildespattes.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aufildespattes.api.entity.WalkImage;
import com.aufildespattes.api.repository.WalkImageRepository;

import lombok.Data;

@Data
@Service
public class WalkImageService {
    
    @Autowired
    private WalkImageRepository walkImageRepository;

    public Optional<WalkImage> getWalk(final long id) {
        return walkImageRepository.findById(id);
    }

    public Iterable<WalkImage> getWalks() {
        return walkImageRepository.findAll();
    }

    public void deleteWalk(final Long id) {
        walkImageRepository.deleteById(id);
    }

    public WalkImage saveWalkImage(WalkImage walkImage) {
       return walkImageRepository.save(walkImage);
    }

}
