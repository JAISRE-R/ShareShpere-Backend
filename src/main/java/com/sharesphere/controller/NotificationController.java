package com.sharesphere.controller;

import com.sharesphere.dto.NotificationDTO;
import com.sharesphere.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getMyNotifications(Authentication authentication) {
        return ResponseEntity.ok(notificationService.getUserNotifications(authentication.getName()));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long id,
            Authentication authentication
    ) {
        notificationService.markAsRead(id, authentication.getName());
        return ResponseEntity.ok().build();
    }


    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

}