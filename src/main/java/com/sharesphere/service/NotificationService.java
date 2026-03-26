package com.sharesphere.service;

import com.sharesphere.dto.NotificationDTO;
import com.sharesphere.model.Notification;
import com.sharesphere.model.User;
import com.sharesphere.repository.NotificationRepository;
import com.sharesphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public List<NotificationDTO> getUserNotifications(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return notificationRepository.findByUserIdOrderByTimestampDesc(user.getId())
                .stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    public void markAsRead(Long notificationId, String email) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
                
        if (!notification.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }
        
        notification.setReadStatus(true);
        notificationRepository.save(notification);
    }

    // Called internally by other services (e.g. BorrowRequestService)
    public void createNotification(User user, String message, String type) {
        Notification notification = Notification.builder()
                .user(user)
                .message(message)
                .type(type)
                .build();
        notificationRepository.save(notification);
    }
    
    private NotificationDTO toDTO(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .userId(notification.getUser().getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .readStatus(notification.getReadStatus())
                .timestamp(notification.getTimestamp())
                .build();
    }


    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

}