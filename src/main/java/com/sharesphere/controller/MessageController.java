package com.sharesphere.controller;

import com.sharesphere.dto.MessageDTO;
import com.sharesphere.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(
            @RequestBody MessageDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(messageService.sendMessage(dto, authentication.getName()));
    }

    @GetMapping("/conversation/{userId}")
    public ResponseEntity<List<MessageDTO>> getConversation(
            @PathVariable Long userId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(messageService.getConversation(userId, authentication.getName()));
    }

    @GetMapping("/resource/{resourceId}")
    public ResponseEntity<List<MessageDTO>> getResourceMessages(
            @PathVariable Long resourceId
    ) {
        return ResponseEntity.ok(messageService.getResourceMessages(resourceId));
    }


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

}