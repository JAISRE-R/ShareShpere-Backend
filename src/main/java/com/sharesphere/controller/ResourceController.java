package com.sharesphere.controller;

import com.sharesphere.dto.ResourceDTO;
import com.sharesphere.service.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public ResponseEntity<Page<ResourceDTO>> getAllResources(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateListed") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        return ResponseEntity.ok(resourceService.getAllResources(keyword, category, status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(
            @RequestBody ResourceDTO resourceDTO,
            Authentication authentication
    ) {
        return ResponseEntity.ok(resourceService.createResource(resourceDTO, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO> updateResource(
            @PathVariable Long id,
            @RequestBody ResourceDTO resourceDTO,
            Authentication authentication
    ) {
        return ResponseEntity.ok(resourceService.updateResource(id, resourceDTO, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(
            @PathVariable Long id,
            Authentication authentication
    ) {
        resourceService.deleteResource(id, authentication.getName());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResourceDTO>> getUserResources(@PathVariable Long userId) {
        return ResponseEntity.ok(resourceService.getResourcesByOwner(userId));
    }


    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

}