package com.sharesphere.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String message;

    private String type; // REQUEST, APPROVED, REJECTED, MESSAGE, SYSTEM
    
    private boolean readStatus;

    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
        readStatus = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean getReadStatus() { return readStatus; }
    public void setReadStatus(boolean readStatus) { this.readStatus = readStatus; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Notification() {}

    public Notification(Long id, User user, String message, String type, boolean readStatus, LocalDateTime timestamp) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.type = type;
        this.readStatus = readStatus;
        this.timestamp = timestamp;
    }

    public static NotificationBuilder builder() { return new NotificationBuilder(); }

    public static class NotificationBuilder {
        private Long id;
        private User user;
        private String message;
        private String type;
        private boolean readStatus;
        private LocalDateTime timestamp;

        public NotificationBuilder id(Long id) { this.id = id; return this; }
        public NotificationBuilder user(User user) { this.user = user; return this; }
        public NotificationBuilder message(String message) { this.message = message; return this; }
        public NotificationBuilder type(String type) { this.type = type; return this; }
        public NotificationBuilder readStatus(boolean readStatus) { this.readStatus = readStatus; return this; }
        public NotificationBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public Notification build() { return new Notification(this.id, this.user, this.message, this.type, this.readStatus, this.timestamp); }
    }

}