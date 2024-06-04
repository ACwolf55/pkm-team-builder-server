package com.example.repository;

import com.example.entity.PkmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PkmUserRepository extends JpaRepository<PkmUser, Integer> {
    // Additional query methods can be defined here if needed
}
