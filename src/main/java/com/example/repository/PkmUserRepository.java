package com.example.repository;
import java.util.Optional;

import com.example.entity.PkmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PkmUserRepository extends JpaRepository<PkmUser, Integer> {
    boolean existsByUserName(String username); 
    PkmUser findByUsername(String username);
}
