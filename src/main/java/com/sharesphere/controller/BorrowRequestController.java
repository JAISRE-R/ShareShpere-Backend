package com.sharesphere.controller;

import com.sharesphere.dto.BorrowRequestDTO;
import com.sharesphere.service.BorrowRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/requests")
public class BorrowRequestController {

    private final BorrowRequestService service;

    @PostMapping
    public ResponseEntity<BorrowRequestDTO> createRequest(
            @RequestBody BorrowRequestDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.createRequest(dto, authentication.getName()));
    }

    @GetMapping("/incoming")
    public ResponseEntity<List<BorrowRequestDTO>> getIncomingRequests(Authentication authentication) {
        return ResponseEntity.ok(service.getIncomingRequests(authentication.getName()));
    }

    @GetMapping("/outgoing")
    public ResponseEntity<List<BorrowRequestDTO>> getOutgoingRequests(Authentication authentication) {
        return ResponseEntity.ok(service.getOutgoingRequests(authentication.getName()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BorrowRequestDTO> updateRequestStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusMap,
            Authentication authentication
    ) {
        String status = statusMap.get("status");
        return ResponseEntity.ok(service.updateRequestStatus(id, status, authentication.getName()));
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<BorrowRequestDTO> approveRequest(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.updateRequestStatus(id, "APPROVED", authentication.getName()));
    }
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<BorrowRequestDTO> rejectRequest(
            @PathVariable Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.updateRequestStatus(id, "REJECTED", authentication.getName()));
    }


    public BorrowRequestController(BorrowRequestService service) {
        this.service = service;
    }

}