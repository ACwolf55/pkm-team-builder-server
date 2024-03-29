package com.example.repository;
import com.example.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPARepository that will be used to preform persistence operations on Message objects
 */
public interface MessageRepository extends JpaRepository <Message, Integer> {
    // @Query(value = "SELECT * FROM message WHERE posted_by = :postedBy", nativeQuery = true)
    // List<Message> findByPostedBy(@Param("posted_by") int posted_by);
}