package com.example.service;

import com.example.entity.Trainer;
import com.example.repository.TrainerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
@Transactional
public class TrainerService {

    private TrainerRepository trainerRepository;
 
    @Autowired
    public TrainerService(TrainerRepository trainerRepository ) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer register(Trainer trainer) {
        String trainerName = trainer.getTrainerName();

        boolean trainerExists = trainerRepository.existsByTrainerName(trainerName);
        if (trainerExists) {
            return null;
        }

        if (trainer.getTrainerName() == null || trainer.getTrainerName().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (trainer.getPassword() == null || trainer.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        
        return trainerRepository.save(trainer);
    }

    public Optional<Trainer> login(Trainer trainer) {
        String trainerName = trainer.getTrainerName();
        String password = trainer.getPassword();

        Trainer authTrainer = trainerRepository.findByTrainerName(trainerName);

        if (authTrainer != null && authTrainer.getPassword().equals(password)) {
            return Optional.of(authTrainer);
        } else {
            return Optional.empty();
        }
    }

}