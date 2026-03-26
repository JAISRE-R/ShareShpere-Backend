package com.sharesphere.dto;

import com.sharesphere.model.BorrowRequest;
import org.springframework.stereotype.Component;

@Component
public class BorrowRequestMapper {

    public BorrowRequestDTO toDTO(BorrowRequest request) {
        if (request == null) return null;

        return BorrowRequestDTO.builder()
                .id(request.getId())
                .requesterId(request.getRequester() != null ? request.getRequester().getId() : null)
                .requesterName(request.getRequester() != null ? request.getRequester().getName() : null)
                .resourceId(request.getResource() != null ? request.getResource().getId() : null)
                .resourceTitle(request.getResource() != null ? request.getResource().getTitle() : null)
                .message(request.getMessage())
                .status(request.getStatus())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .pickupMethod(request.getPickupMethod())
                .createdAt(request.getCreatedAt())
                .build();
    }
}
