package com.sharesphere.repository;

import com.sharesphere.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    Page<Resource> findByAvailabilityStatus(String availabilityStatus, Pageable pageable);
    
    Page<Resource> findByCategoryAndAvailabilityStatus(String category, String availabilityStatus, Pageable pageable);
    
    @Query("SELECT r FROM Resource r WHERE " +
           "(LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(r.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "AND r.availabilityStatus = :status")
    Page<Resource> searchResources(@Param("keyword") String keyword, @Param("status") String status, Pageable pageable);
    
    List<Resource> findByOwnerId(Long ownerId);
}
