package com.sharesphere.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private User reviewee;

    @Column(nullable = false)
    private Integer stars;

    @Column(length = 1000)
    private String comment;

    private LocalDateTime timestamp;
    
    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getReviewer() { return reviewer; }
    public void setReviewer(User reviewer) { this.reviewer = reviewer; }
    public User getReviewee() { return reviewee; }
    public void setReviewee(User reviewee) { this.reviewee = reviewee; }
    public Integer getStars() { return stars; }
    public void setStars(Integer stars) { this.stars = stars; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Rating() {}

    public Rating(Long id, User reviewer, User reviewee, Integer stars, String comment, LocalDateTime timestamp) {
        this.id = id;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.stars = stars;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public static RatingBuilder builder() { return new RatingBuilder(); }

    public static class RatingBuilder {
        private Long id;
        private User reviewer;
        private User reviewee;
        private Integer stars;
        private String comment;
        private LocalDateTime timestamp;

        public RatingBuilder id(Long id) { this.id = id; return this; }
        public RatingBuilder reviewer(User reviewer) { this.reviewer = reviewer; return this; }
        public RatingBuilder reviewee(User reviewee) { this.reviewee = reviewee; return this; }
        public RatingBuilder stars(Integer stars) { this.stars = stars; return this; }
        public RatingBuilder comment(String comment) { this.comment = comment; return this; }
        public RatingBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public Rating build() { return new Rating(this.id, this.reviewer, this.reviewee, this.stars, this.comment, this.timestamp); }
    }

}