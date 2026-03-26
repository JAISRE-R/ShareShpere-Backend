package com.sharesphere.dto;

import com.sharesphere.model.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {
    
    public ResourceDTO toDTO(Resource resource) {
        if (resource == null) return null;
        
        return ResourceDTO.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .description(resource.getDescription())
                .category(resource.getCategory())
                .condition(resource.getCondition())
                .location(resource.getLocation())
                .availabilityStatus(resource.getAvailabilityStatus())
                .ownerId(resource.getOwner() != null ? resource.getOwner().getId() : null)
                .ownerName(resource.getOwner() != null ? resource.getOwner().getName() : null)
                .images(resource.getImages())
                .tags(resource.getTags())
                .dateListed(resource.getDateListed())
                .viewCount(resource.getViewCount())
                .requestCount(resource.getRequestCount())
                .build();
    }
}
