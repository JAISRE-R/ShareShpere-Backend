package com.sharesphere.repository;

import com.sharesphere.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRevieweeId(Long revieweeId);
    List<Rating> findByReviewerId(Long reviewerId);
}
