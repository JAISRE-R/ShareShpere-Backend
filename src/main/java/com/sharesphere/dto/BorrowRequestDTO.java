package com.sharesphere.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class BorrowRequestDTO {
    private Long id;
    private Long requesterId;
    private String requesterName;
    private Long resourceId;
    private String resourceTitle;
    private String message;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String pickupMethod;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRequesterId() { return requesterId; }
    public void setRequesterId(Long requesterId) { this.requesterId = requesterId; }
    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }
    public Long getResourceId() { return resourceId; }
    public void setResourceId(Long resourceId) { this.resourceId = resourceId; }
    public String getResourceTitle() { return resourceTitle; }
    public void setResourceTitle(String resourceTitle) { this.resourceTitle = resourceTitle; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getPickupMethod() { return pickupMethod; }
    public void setPickupMethod(String pickupMethod) { this.pickupMethod = pickupMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public BorrowRequestDTO() {}

    public BorrowRequestDTO(Long id, Long requesterId, String requesterName, Long resourceId, String resourceTitle, String message, String status, LocalDate startDate, LocalDate endDate, String pickupMethod, LocalDateTime createdAt) {
        this.id = id;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.resourceId = resourceId;
        this.resourceTitle = resourceTitle;
        this.message = message;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupMethod = pickupMethod;
        this.createdAt = createdAt;
    }

    public static BorrowRequestDTOBuilder builder() { return new BorrowRequestDTOBuilder(); }

    public static class BorrowRequestDTOBuilder {
        private Long id;
        private Long requesterId;
        private String requesterName;
        private Long resourceId;
        private String resourceTitle;
        private String message;
        private String status;
        private LocalDate startDate;
        private LocalDate endDate;
        private String pickupMethod;
        private LocalDateTime createdAt;

        public BorrowRequestDTOBuilder id(Long id) { this.id = id; return this; }
        public BorrowRequestDTOBuilder requesterId(Long requesterId) { this.requesterId = requesterId; return this; }
        public BorrowRequestDTOBuilder requesterName(String requesterName) { this.requesterName = requesterName; return this; }
        public BorrowRequestDTOBuilder resourceId(Long resourceId) { this.resourceId = resourceId; return this; }
        public BorrowRequestDTOBuilder resourceTitle(String resourceTitle) { this.resourceTitle = resourceTitle; return this; }
        public BorrowRequestDTOBuilder message(String message) { this.message = message; return this; }
        public BorrowRequestDTOBuilder status(String status) { this.status = status; return this; }
        public BorrowRequestDTOBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public BorrowRequestDTOBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public BorrowRequestDTOBuilder pickupMethod(String pickupMethod) { this.pickupMethod = pickupMethod; return this; }
        public BorrowRequestDTOBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public BorrowRequestDTO build() { return new BorrowRequestDTO(this.id, this.requesterId, this.requesterName, this.resourceId, this.resourceTitle, this.message, this.status, this.startDate, this.endDate, this.pickupMethod, this.createdAt); }
    }

}