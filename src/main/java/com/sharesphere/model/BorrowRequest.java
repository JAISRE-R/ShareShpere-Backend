package com.sharesphere.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_requests")
public class BorrowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(length = 1000)
    private String message;

    private String status; // PENDING, APPROVED, REJECTED, BORROWED, RETURNED

    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private String pickupMethod;

    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = "PENDING";
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }
    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }
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

    public BorrowRequest() {}

    public BorrowRequest(Long id, User requester, Resource resource, String message, String status, LocalDate startDate, LocalDate endDate, String pickupMethod, LocalDateTime createdAt) {
        this.id = id;
        this.requester = requester;
        this.resource = resource;
        this.message = message;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupMethod = pickupMethod;
        this.createdAt = createdAt;
    }

    public static BorrowRequestBuilder builder() { return new BorrowRequestBuilder(); }

    public static class BorrowRequestBuilder {
        private Long id;
        private User requester;
        private Resource resource;
        private String message;
        private String status;
        private LocalDate startDate;
        private LocalDate endDate;
        private String pickupMethod;
        private LocalDateTime createdAt;

        public BorrowRequestBuilder id(Long id) { this.id = id; return this; }
        public BorrowRequestBuilder requester(User requester) { this.requester = requester; return this; }
        public BorrowRequestBuilder resource(Resource resource) { this.resource = resource; return this; }
        public BorrowRequestBuilder message(String message) { this.message = message; return this; }
        public BorrowRequestBuilder status(String status) { this.status = status; return this; }
        public BorrowRequestBuilder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public BorrowRequestBuilder endDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public BorrowRequestBuilder pickupMethod(String pickupMethod) { this.pickupMethod = pickupMethod; return this; }
        public BorrowRequestBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public BorrowRequest build() { return new BorrowRequest(this.id, this.requester, this.resource, this.message, this.status, this.startDate, this.endDate, this.pickupMethod, this.createdAt); }
    }

}