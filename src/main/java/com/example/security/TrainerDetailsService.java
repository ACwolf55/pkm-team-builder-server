package com.example.security;

import com.example.entity.Trainer;  
import com.example.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrainerDetailsService implements UserDetailsService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Trainer trainer = trainerRepository.findByTrainerName(username);
        
        if (trainer == null) {
            throw new UsernameNotFoundException("Trainer not found: " + username);
        }

        return new User(
            trainer.getTrainerName(),
            trainer.getPassword(),
            new ArrayList<>()
        );
    }
}