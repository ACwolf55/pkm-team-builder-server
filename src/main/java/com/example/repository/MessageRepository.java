package com.example.repository;
import com.example.entity.Message;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPARepository that will be used to preform persistence operations on Message objects
 */
public interface MessageRepository extends JpaRepository <Message, Integer> {
    // Optional<Message> findById(Long messageId);
}