package com.sharesphere.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String category;
    
    @Column(name = "item_condition") // 'condition' is a reserved word in some DBs
    private String condition;

    private String location;

    private String availabilityStatus; // "AVAILABLE", "UNAVAILABLE", "BORROWED"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<String> tags;

    private LocalDateTime dateListed;

    private int viewCount;

    private int requestCount;
    
    @PrePersist
    protected void onCreate() {
        dateListed = LocalDateTime.now();
        if(availabilityStatus == null) {
            availabilityStatus = "AVAILABLE";
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public LocalDateTime getDateListed() { return dateListed; }
    public void setDateListed(LocalDateTime dateListed) { this.dateListed = dateListed; }
    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
    public int getRequestCount() { return requestCount; }
    public void setRequestCount(int requestCount) { this.requestCount = requestCount; }

    public Resource() {}

    public Resource(Long id, String title, String description, String category, String condition, String location, String availabilityStatus, User owner, List<String> images, List<String> tags, LocalDateTime dateListed, int viewCount, int requestCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.condition = condition;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
        this.owner = owner;
        this.images = images;
        this.tags = tags;
        this.dateListed = dateListed;
        this.viewCount = viewCount;
        this.requestCount = requestCount;
    }

    public static ResourceBuilder builder() { return new ResourceBuilder(); }

    public static class ResourceBuilder {
        private Long id;
        private String title;
        private String description;
        private String category;
        private String condition;
        private String location;
        private String availabilityStatus;
        private User owner;
        private List<String> images;
        private List<String> tags;
        private LocalDateTime dateListed;
        private int viewCount;
        private int requestCount;

        public ResourceBuilder id(Long id) { this.id = id; return this; }
        public ResourceBuilder title(String title) { this.title = title; return this; }
        public ResourceBuilder description(String description) { this.description = description; return this; }
        public ResourceBuilder category(String category) { this.category = category; return this; }
        public ResourceBuilder condition(String condition) { this.condition = condition; return this; }
        public ResourceBuilder location(String location) { this.location = location; return this; }
        public ResourceBuilder availabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; return this; }
        public ResourceBuilder owner(User owner) { this.owner = owner; return this; }
        public ResourceBuilder images(List<String> images) { this.images = images; return this; }
        public ResourceBuilder tags(List<String> tags) { this.tags = tags; return this; }
        public ResourceBuilder dateListed(LocalDateTime dateListed) { this.dateListed = dateListed; return this; }
        public ResourceBuilder viewCount(int viewCount) { this.viewCount = viewCount; return this; }
        public ResourceBuilder requestCount(int requestCount) { this.requestCount = requestCount; return this; }
        public Resource build() { return new Resource(this.id, this.title, this.description, this.category, this.condition, this.location, this.availabilityStatus, this.owner, this.images, this.tags, this.dateListed, this.viewCount, this.requestCount); }
    }

}