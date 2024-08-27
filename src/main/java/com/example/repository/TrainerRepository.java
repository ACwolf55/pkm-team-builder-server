package com.example.repository;
import java.util.Optional;

import com.example.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    boolean existsByUserName(String trainerName); 
    Trainer findByUserName(String trainerName);
}
