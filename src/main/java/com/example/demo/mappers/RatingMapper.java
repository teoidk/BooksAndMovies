package com.example.demo.mappers;

import com.example.demo.controllers.dtos.RatingDto;
import com.example.demo.persistence.entities.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RatingMapper {

    public RatingDto toDto(Rating rating) {
        return new RatingDto(rating.getId(), rating.getReview(), rating.getRating());
    }

    public Rating toEntity(RatingDto ratingDto) {
        Rating rating = new Rating();
        rating.setReview(ratingDto.review());
        rating.setRating(ratingDto.rating());
        return rating;
    }
}
