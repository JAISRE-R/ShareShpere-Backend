package com.sharesphere.controller;

import com.sharesphere.dto.RatingDTO;
import com.sharesphere.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    private final RatingService service;

    @PostMapping
    public ResponseEntity<RatingDTO> submitRating(
            @RequestBody RatingDTO dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.submitRating(dto, authentication.getName()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RatingDTO>> getUserRatings(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserRatings(userId));
    }


    public RatingController(RatingService service) {
        this.service = service;
    }

}