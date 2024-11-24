package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Image;
import com.airbnb.entity.Property;
import com.airbnb.repository.ImageRepository;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private ImageRepository imageRepository;
    private PropertyRepository propertyRepository;
    private BucketService bucketService;


    public ImageController(ImageRepository imageRepository, PropertyRepository propertyRepository, BucketService bucketService) {
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
    }

    @PostMapping(
            path = "/upload/file/{bucketName}/property/{propertyId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file,
                                        @PathVariable String bucketName,
                                        @PathVariable long propertyId,
                                        @AuthenticationPrincipal AppUser user) throws IOException {
        String url=bucketService.uploadFile(file,bucketName);
        Property property=propertyRepository.findById(propertyId).get();

        Image img=new Image();
        img.setUrl(url);
        img.setProperty(property);

        Image savedImage=imageRepository.save(img);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }


}
