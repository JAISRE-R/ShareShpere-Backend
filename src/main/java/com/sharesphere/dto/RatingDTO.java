package com.sharesphere.dto;


import java.time.LocalDateTime;

public class RatingDTO {
    private Long id;
    private Long reviewerId;
    private String reviewerName;
    private Long revieweeId;
    private Integer stars;
    private String comment;
    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getReviewerId() { return reviewerId; }
    public void setReviewerId(Long reviewerId) { this.reviewerId = reviewerId; }
    public String getReviewerName() { return reviewerName; }
    public void setReviewerName(String reviewerName) { this.reviewerName = reviewerName; }
    public Long getRevieweeId() { return revieweeId; }
    public void setRevieweeId(Long revieweeId) { this.revieweeId = revieweeId; }
    public Integer getStars() { return stars; }
    public void setStars(Integer stars) { this.stars = stars; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public RatingDTO() {}

    public RatingDTO(Long id, Long reviewerId, String reviewerName, Long revieweeId, Integer stars, String comment, LocalDateTime timestamp) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.reviewerName = reviewerName;
        this.revieweeId = revieweeId;
        this.stars = stars;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public static RatingDTOBuilder builder() { return new RatingDTOBuilder(); }

    public static class RatingDTOBuilder {
        private Long id;
        private Long reviewerId;
        private String reviewerName;
        private Long revieweeId;
        private Integer stars;
        private String comment;
        private LocalDateTime timestamp;

        public RatingDTOBuilder id(Long id) { this.id = id; return this; }
        public RatingDTOBuilder reviewerId(Long reviewerId) { this.reviewerId = reviewerId; return this; }
        public RatingDTOBuilder reviewerName(String reviewerName) { this.reviewerName = reviewerName; return this; }
        public RatingDTOBuilder revieweeId(Long revieweeId) { this.revieweeId = revieweeId; return this; }
        public RatingDTOBuilder stars(Integer stars) { this.stars = stars; return this; }
        public RatingDTOBuilder comment(String comment) { this.comment = comment; return this; }
        public RatingDTOBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public RatingDTO build() { return new RatingDTO(this.id, this.reviewerId, this.reviewerName, this.revieweeId, this.stars, this.comment, this.timestamp); }
    }

}