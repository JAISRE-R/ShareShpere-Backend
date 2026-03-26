package com.sharesphere.repository;

import com.sharesphere.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimestampAsc(
        Long senderId1, Long receiverId1, Long senderId2, Long receiverId2);
        
    List<Message> findByResourceIdOrderByTimestampAsc(Long resourceId);
}
