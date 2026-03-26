package com.sharesphere.dto;


import java.time.LocalDateTime;

public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private String type;
    private boolean readStatus;
    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean getReadStatus() { return readStatus; }
    public void setReadStatus(boolean readStatus) { this.readStatus = readStatus; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public NotificationDTO() {}

    public NotificationDTO(Long id, Long userId, String message, String type, boolean readStatus, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.type = type;
        this.readStatus = readStatus;
        this.timestamp = timestamp;
    }

    public static NotificationDTOBuilder builder() { return new NotificationDTOBuilder(); }

    public static class NotificationDTOBuilder {
        private Long id;
        private Long userId;
        private String message;
        private String type;
        private boolean readStatus;
        private LocalDateTime timestamp;

        public NotificationDTOBuilder id(Long id) { this.id = id; return this; }
        public NotificationDTOBuilder userId(Long userId) { this.userId = userId; return this; }
        public NotificationDTOBuilder message(String message) { this.message = message; return this; }
        public NotificationDTOBuilder type(String type) { this.type = type; return this; }
        public NotificationDTOBuilder readStatus(boolean readStatus) { this.readStatus = readStatus; return this; }
        public NotificationDTOBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public NotificationDTO build() { return new NotificationDTO(this.id, this.userId, this.message, this.type, this.readStatus, this.timestamp); }
    }

}