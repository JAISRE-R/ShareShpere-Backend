package com.sharesphere.repository;

import com.sharesphere.model.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Long> {
    List<BorrowRequest> findByRequesterId(Long requesterId);
    List<BorrowRequest> findByResourceOwnerId(Long ownerId);
    List<BorrowRequest> findByResourceId(Long resourceId);
}
