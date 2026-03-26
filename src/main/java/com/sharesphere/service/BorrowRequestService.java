package com.sharesphere.service;

import com.sharesphere.dto.BorrowRequestDTO;
import com.sharesphere.dto.BorrowRequestMapper;
import com.sharesphere.model.BorrowRequest;
import com.sharesphere.model.Resource;
import com.sharesphere.model.User;
import com.sharesphere.repository.BorrowRequestRepository;
import com.sharesphere.repository.ResourceRepository;
import com.sharesphere.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRequestService {

    private final BorrowRequestRepository borrowRequestRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final BorrowRequestMapper mapper;

    public BorrowRequestDTO createRequest(BorrowRequestDTO dto, String requesterEmail) {
        User requester = userRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Resource resource = resourceRepository.findById(dto.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
                
        if (resource.getOwner().getId().equals(requester.getId())) {
            throw new RuntimeException("Cannot borrow your own resource");
        }
        
        BorrowRequest request = BorrowRequest.builder()
                .requester(requester)
                .resource(resource)
                .message(dto.getMessage())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .pickupMethod(dto.getPickupMethod())
                .status("PENDING")
                .build();
                
        resource.setRequestCount(resource.getRequestCount() + 1);
        resourceRepository.save(resource);
                
        return mapper.toDTO(borrowRequestRepository.save(request));
    }

    public List<BorrowRequestDTO> getIncomingRequests(String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return borrowRequestRepository.findByResourceOwnerId(owner.getId()).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BorrowRequestDTO> getOutgoingRequests(String requesterEmail) {
        User requester = userRepository.findByEmail(requesterEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        return borrowRequestRepository.findByRequesterId(requester.getId()).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public BorrowRequestDTO updateRequestStatus(Long requestId, String status, String ownerEmail) {
        BorrowRequest request = borrowRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
                
        if (!request.getResource().getOwner().getEmail().equals(ownerEmail) && 
            !request.getRequester().getEmail().equals(ownerEmail)) {
            // Requester can mark as 'RETURNED', Owner can mark 'APPROVED', 'REJECTED'
            throw new RuntimeException("Not authorized to update this request");
        }
        
        request.setStatus(status.toUpperCase());
        
        if ("APPROVED".equalsIgnoreCase(status) || "BORROWED".equalsIgnoreCase(status)) {
            request.getResource().setAvailabilityStatus("BORROWED");
            resourceRepository.save(request.getResource());
        } else if ("RETURNED".equalsIgnoreCase(status) || "REJECTED".equalsIgnoreCase(status)) {
            request.getResource().setAvailabilityStatus("AVAILABLE");
            resourceRepository.save(request.getResource());
        }
        
        return mapper.toDTO(borrowRequestRepository.save(request));
    }


    public BorrowRequestService(BorrowRequestRepository borrowRequestRepository, ResourceRepository resourceRepository, UserRepository userRepository, BorrowRequestMapper mapper) {
        this.borrowRequestRepository = borrowRequestRepository;
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

}