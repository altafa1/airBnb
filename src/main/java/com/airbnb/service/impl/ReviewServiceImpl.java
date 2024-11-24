package com.airbnb.service.impl;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Review;
import com.airbnb.exception.ReviewExist;
import com.airbnb.exception.UserExistException;
import com.airbnb.payload.UserDto;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import com.airbnb.service.ReviewService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Review createReview(Review review, long propertyId, AppUser appUser) {
        Optional<Property> opProperty=propertyRepository.findById(propertyId);
        if(opProperty.isPresent()){
            Property property=opProperty.get();
            Review review1=reviewRepository.findByUserAndProperty(appUser,property);
            if (review1!=null){
                throw new ReviewExist("this user can't add multiple reviews");
            }
            review.setProperty(property);
            review.setAppUser(appUser);

           return reviewRepository.save(review);

        }
        else
        {
            throw new UserExistException("shkbgsjd");
        }
    }

    @Override
    public  List<Review> findByUserReview(AppUser user) {
       List<Review> reviews=reviewRepository.findByUser(user);
       if(reviews!=null){
           return reviews;
       }

        return null;
    }

    AppUser mapToEntity(UserDto dto){
        return modelMapper.map(dto,AppUser.class);
    }
}
