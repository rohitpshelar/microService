package com.example.rating.services.impl;

import com.example.rating.entities.Rating;
import com.example.rating.repositories.RatingRepo;
import com.example.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Rating create(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String uId) {
        return ratingRepo.findByUserId(uId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hId) {
        return ratingRepo.findByHotelId(hId);
    }
}
