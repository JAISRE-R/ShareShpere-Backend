package com.sharesphere.service;

import com.sharesphere.dto.ResourceDTO;
import com.sharesphere.dto.ResourceMapper;
import com.sharesphere.model.Resource;
import com.sharesphere.model.User;
import com.sharesphere.repository.ResourceRepository;
import com.sharesphere.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final ResourceMapper resourceMapper;

    public Page<ResourceDTO> getAllResources(String keyword, String category, String status, Pageable pageable) {
        Page<Resource> resources;
        
        if (keyword != null && !keyword.isEmpty()) {
            resources = resourceRepository.searchResources(keyword, status != null ? status : "AVAILABLE", pageable);
        } else if (category != null && !category.isEmpty()) {
            resources = resourceRepository.findByCategoryAndAvailabilityStatus(category, status != null ? status : "AVAILABLE", pageable);
        } else if (status != null && !status.isEmpty()) {
            resources = resourceRepository.findByAvailabilityStatus(status, pageable);
        } else {
            resources = resourceRepository.findAll(pageable);
        }
        
        return resources.map(resourceMapper::toDTO);
    }

    public ResourceDTO getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        // Increment view count
        resource.setViewCount(resource.getViewCount() + 1);
        resourceRepository.save(resource);
        return resourceMapper.toDTO(resource);
    }

    public ResourceDTO createResource(ResourceDTO dto, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        Resource resource = Resource.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .condition(dto.getCondition())
                .location(dto.getLocation())
                .images(dto.getImages())
                .tags(dto.getTags())
                .owner(owner)
                .build();
                
        return resourceMapper.toDTO(resourceRepository.save(resource));
    }

    public ResourceDTO updateResource(Long id, ResourceDTO dto, String userEmail) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
                
        if (!resource.getOwner().getEmail().equals(userEmail)) {
            throw new RuntimeException("Not authorized to update this resource");
        }
        
        resource.setTitle(dto.getTitle());
        resource.setDescription(dto.getDescription());
        resource.setCategory(dto.getCategory());
        resource.setCondition(dto.getCondition());
        resource.setLocation(dto.getLocation());
        resource.setAvailabilityStatus(dto.getAvailabilityStatus());
        resource.setImages(dto.getImages());
        resource.setTags(dto.getTags());
        
        return resourceMapper.toDTO(resourceRepository.save(resource));
    }

    public void deleteResource(Long id, String userEmail) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
                
        if (!resource.getOwner().getEmail().equals(userEmail)) {
            throw new RuntimeException("Not authorized to delete this resource");
        }
        
        resourceRepository.delete(resource);
    }
    
    public List<ResourceDTO> getResourcesByOwner(Long ownerId) {
        return resourceRepository.findByOwnerId(ownerId).stream()
                .map(resourceMapper::toDTO)
                .collect(Collectors.toList());
    }


    public ResourceService(ResourceRepository resourceRepository, UserRepository userRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
        this.resourceMapper = resourceMapper;
    }

}