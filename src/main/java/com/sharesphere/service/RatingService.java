package com.sharesphere.service;

import com.sharesphere.dto.RatingDTO;
import com.sharesphere.dto.RatingMapper;
import com.sharesphere.model.Rating;
import com.sharesphere.model.User;
import com.sharesphere.repository.RatingRepository;
import com.sharesphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RatingMapper mapper;

    public RatingDTO submitRating(RatingDTO dto, String reviewerEmail) {
        User reviewer = userRepository.findByEmail(reviewerEmail)
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));
                
        User reviewee = userRepository.findById(dto.getRevieweeId())
                .orElseThrow(() -> new RuntimeException("Reviewee not found"));
                
        if (reviewer.getId().equals(reviewee.getId())) {
            throw new RuntimeException("Cannot rate yourself");
        }
        
        Rating rating = Rating.builder()
                .reviewer(reviewer)
                .reviewee(reviewee)
                .stars(dto.getStars())
                .comment(dto.getComment())
                .build();
                
        rating = ratingRepository.save(rating);
        
        // Update reviewee average rating
        updateUserAverageRating(reviewee);
        
        return mapper.toDTO(rating);
    }
    
    private void updateUserAverageRating(User user) {
        List<Rating> ratings = ratingRepository.findByRevieweeId(user.getId());
        if (!ratings.isEmpty()) {
            double avg = ratings.stream()
                    .mapToInt(Rating::getStars)
                    .average()
                    .orElse(0.0);
            user.setRating(avg);
            userRepository.save(user);
        }
    }

    public List<RatingDTO> getUserRatings(Long userId) {
        return ratingRepository.findByRevieweeId(userId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, RatingMapper mapper) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

}