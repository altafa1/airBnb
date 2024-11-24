package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review review, long propertyId, AppUser dto);

   List<Review> findByUserReview(AppUser user);
}
