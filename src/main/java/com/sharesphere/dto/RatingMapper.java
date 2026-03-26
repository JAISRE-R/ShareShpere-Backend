package com.sharesphere.dto;

import com.sharesphere.model.Rating;
import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    public RatingDTO toDTO(Rating rating) {
        if (rating == null) return null;

        return RatingDTO.builder()
                .id(rating.getId())
                .reviewerId(rating.getReviewer() != null ? rating.getReviewer().getId() : null)
                .reviewerName(rating.getReviewer() != null ? rating.getReviewer().getName() : null)
                .revieweeId(rating.getReviewee() != null ? rating.getReviewee().getId() : null)
                .stars(rating.getStars())
                .comment(rating.getComment())
                .timestamp(rating.getTimestamp())
                .build();
    }
}
