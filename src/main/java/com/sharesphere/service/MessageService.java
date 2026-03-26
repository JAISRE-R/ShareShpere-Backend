package com.sharesphere.service;

import com.sharesphere.dto.MessageDTO;
import com.sharesphere.model.Message;
import com.sharesphere.model.Resource;
import com.sharesphere.model.User;
import com.sharesphere.repository.MessageRepository;
import com.sharesphere.repository.ResourceRepository;
import com.sharesphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public MessageDTO sendMessage(MessageDTO dto, String senderEmail) {
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));
        Resource resource = resourceRepository.findById(dto.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        Message message = Message.builder()
                .sender(sender)
                .receiver(receiver)
                .resource(resource)
                .content(dto.getContent())
                .build();
                
        message = messageRepository.save(message);
        return toDTO(message);
    }
    
    public List<MessageDTO> getConversation(Long userId, String currentUserEmail) {
        User currentUser = userRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
                currentUser.getId(), userId, userId, currentUser.getId()
        ).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MessageDTO> getResourceMessages(Long resourceId) {
        return messageRepository.findByResourceIdOrderByTimestampAsc(resourceId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private MessageDTO toDTO(Message message) {
        return MessageDTO.builder()
                .id(message.getId())
                .senderId(message.getSender().getId())
                .senderName(message.getSender().getName())
                .receiverId(message.getReceiver().getId())
                .resourceId(message.getResource().getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .build();
    }


    public MessageService(MessageRepository messageRepository, UserRepository userRepository, ResourceRepository resourceRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

}