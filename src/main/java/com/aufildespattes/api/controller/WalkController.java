package com.aufildespattes.api.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import com.aufildespattes.api.entity.Walk;
import com.aufildespattes.api.entity.WalkImage;
import com.aufildespattes.api.model.GeoCoding;
import com.aufildespattes.api.service.WalkImageService;
import com.aufildespattes.api.service.WalkService;
import com.aufildespattes.api.utils.Utils;
import org.springframework.core.env.Environment;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
public class WalkController {

    private static final String GEOCODING_URI = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String LANGUAGE = "fr";
    private static final String UPLOAD_DIR = "src/main/resources/static/images/";
    private String API_KEY;

    @Autowired
    private WalkService walkService;

    @Autowired
    private WalkImageService walkImageService;

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
                .queryParam("address", Utils.formatAddress(walk.getStreet()) + walk.getPostalCode())
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

    @PostMapping(path = "/image")
    public WalkImage saveWalkImagre(@RequestParam("image") MultipartFile file,
            @NotBlank @RequestParam("slug") String walkSlug) throws IOException {
        Walk walk = walkService.getWalkBySlug(walkSlug);

        if (walk == null) {
            throw new IllegalArgumentException("Invalid walk slug");
        }

        String fileExtension = FileUploadUtil.getExtension(file.getOriginalFilename());

        if (!Arrays.asList("jpg", "jpeg", "png").contains(fileExtension)) {
            throw new IllegalArgumentException("Invalid file type");
        }

        if (file.getSize() > 1048576) {
            throw new IllegalArgumentException("File size exceeds limit");
        }

        try {

            final String fileName = System.currentTimeMillis() + "-"
                    + StringUtils.cleanPath(file.getOriginalFilename());
            final String uploadDir = UPLOAD_DIR;

            FileUploadUtil.saveFile(uploadDir, fileName, file);

            WalkImage walkImage = new WalkImage();

            walkImage.setName(fileName);
            walkImage.setWalk(walk);
            walkImageService.saveWalkImage(walkImage);

            return walkImage;
        } catch (IOException e) {
            throw new IOException("Could not store file " + file.getOriginalFilename() + ". Please try again!");
        }
    }

    @GetMapping("walk/{slug}")
    public Walk getWalkBySlug(@PathVariable String slug) {
        return walkService.getWalkBySlug(slug);
    }


    @GetMapping("{slug}")
    public void getImages(@PathVariable String slug, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        InputStream is = new FileInputStream(UPLOAD_DIR + slug);
        StreamUtils.copy(is, response.getOutputStream());
    }

}
