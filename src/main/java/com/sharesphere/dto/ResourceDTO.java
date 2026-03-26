package com.sharesphere.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ResourceDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String condition;
    private String location;
    private String availabilityStatus;
    private Long ownerId;
    private String ownerName;
    private List<String> images;
    private List<String> tags;
    private LocalDateTime dateListed;
    private int viewCount;
    private int requestCount;

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
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
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

    public ResourceDTO() {}

    public ResourceDTO(Long id, String title, String description, String category, String condition, String location, String availabilityStatus, Long ownerId, String ownerName, List<String> images, List<String> tags, LocalDateTime dateListed, int viewCount, int requestCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.condition = condition;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.images = images;
        this.tags = tags;
        this.dateListed = dateListed;
        this.viewCount = viewCount;
        this.requestCount = requestCount;
    }

    public static ResourceDTOBuilder builder() { return new ResourceDTOBuilder(); }

    public static class ResourceDTOBuilder {
        private Long id;
        private String title;
        private String description;
        private String category;
        private String condition;
        private String location;
        private String availabilityStatus;
        private Long ownerId;
        private String ownerName;
        private List<String> images;
        private List<String> tags;
        private LocalDateTime dateListed;
        private int viewCount;
        private int requestCount;

        public ResourceDTOBuilder id(Long id) { this.id = id; return this; }
        public ResourceDTOBuilder title(String title) { this.title = title; return this; }
        public ResourceDTOBuilder description(String description) { this.description = description; return this; }
        public ResourceDTOBuilder category(String category) { this.category = category; return this; }
        public ResourceDTOBuilder condition(String condition) { this.condition = condition; return this; }
        public ResourceDTOBuilder location(String location) { this.location = location; return this; }
        public ResourceDTOBuilder availabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; return this; }
        public ResourceDTOBuilder ownerId(Long ownerId) { this.ownerId = ownerId; return this; }
        public ResourceDTOBuilder ownerName(String ownerName) { this.ownerName = ownerName; return this; }
        public ResourceDTOBuilder images(List<String> images) { this.images = images; return this; }
        public ResourceDTOBuilder tags(List<String> tags) { this.tags = tags; return this; }
        public ResourceDTOBuilder dateListed(LocalDateTime dateListed) { this.dateListed = dateListed; return this; }
        public ResourceDTOBuilder viewCount(int viewCount) { this.viewCount = viewCount; return this; }
        public ResourceDTOBuilder requestCount(int requestCount) { this.requestCount = requestCount; return this; }
        public ResourceDTO build() { return new ResourceDTO(this.id, this.title, this.description, this.category, this.condition, this.location, this.availabilityStatus, this.ownerId, this.ownerName, this.images, this.tags, this.dateListed, this.viewCount, this.requestCount); }
    }

}