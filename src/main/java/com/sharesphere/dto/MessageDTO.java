package com.sharesphere.dto;


import java.time.LocalDateTime;

public class MessageDTO {
    private Long id;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private Long resourceId;
    private String content;
    private LocalDateTime timestamp;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSenderId() { return senderId; }
    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public Long getReceiverId() { return receiverId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    public Long getResourceId() { return resourceId; }
    public void setResourceId(Long resourceId) { this.resourceId = resourceId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public MessageDTO() {}

    public MessageDTO(Long id, Long senderId, String senderName, Long receiverId, Long resourceId, String content, LocalDateTime timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.resourceId = resourceId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public static MessageDTOBuilder builder() { return new MessageDTOBuilder(); }

    public static class MessageDTOBuilder {
        private Long id;
        private Long senderId;
        private String senderName;
        private Long receiverId;
        private Long resourceId;
        private String content;
        private LocalDateTime timestamp;

        public MessageDTOBuilder id(Long id) { this.id = id; return this; }
        public MessageDTOBuilder senderId(Long senderId) { this.senderId = senderId; return this; }
        public MessageDTOBuilder senderName(String senderName) { this.senderName = senderName; return this; }
        public MessageDTOBuilder receiverId(Long receiverId) { this.receiverId = receiverId; return this; }
        public MessageDTOBuilder resourceId(Long resourceId) { this.resourceId = resourceId; return this; }
        public MessageDTOBuilder content(String content) { this.content = content; return this; }
        public MessageDTOBuilder timestamp(LocalDateTime timestamp) { this.timestamp = timestamp; return this; }
        public MessageDTO build() { return new MessageDTO(this.id, this.senderId, this.senderName, this.receiverId, this.resourceId, this.content, this.timestamp); }
    }

}