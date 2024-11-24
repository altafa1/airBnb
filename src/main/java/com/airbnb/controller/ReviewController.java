package com.airbnb.controller;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;
import com.airbnb.payload.UserDto;
import com.airbnb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/createReview")
    public ResponseEntity<Review> createReview(
            @RequestBody Review review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser appUser
            ){
        Review review1=reviewService.createReview(review,propertyId,appUser);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);
    }

    @GetMapping("/userReviews")
    public ResponseEntity< List<Review>> userReview(@AuthenticationPrincipal AppUser user){
        List<Review> review=reviewService.findByUserReview(user);
        return new ResponseEntity<>(review,HttpStatus.OK);
    }
}
