package com.aufildespattes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.aufildespattes.api.entity.Walk;
import com.aufildespattes.api.model.GeoCoding;
import com.aufildespattes.api.service.WalkService;
import com.aufildespattes.api.utils.Utils;
import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
public class WalkController {

    private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String LANGUAGE = "fr";
    private String API_KEY;

    @Autowired
    private WalkService walkService;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        API_KEY = env.getProperty("API_KEY");
    }

    @GetMapping("/walks")
    public ResponseEntity<Iterable<Walk>> getWalks() {
        Iterable<Walk> walks = walkService.getWalks();
        return ResponseEntity.ok(walks);
    }

    @PostMapping(path = "/walk")
    public ResponseEntity<Walk> saveWalk(@Valid @RequestBody Walk walk) {

        walk.setSlug(Utils.formatSlug(walk.getName()));

        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GEOCODING_URI)
                .queryParam("address", Utils.formatAddress(walk.getStreet()))
                .queryParam("key", API_KEY)
                .queryParam("language", LANGUAGE);
        try {
            GeoCoding geoCoding = restTemplate.getForObject(builder.toUriString(), GeoCoding.class);
            Double latitude = geoCoding.getGeoCodingResults()[0].getGeometry().getLocation().getLat();
            Double longitude = geoCoding.getGeoCodingResults()[0].getGeometry().getLocation().getLng();
            walk.setLatitude(latitude);
            walk.setLongitude(longitude);
            Walk savedWalk = walkService.saveWalk(walk);
            return ResponseEntity.ok(savedWalk);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
