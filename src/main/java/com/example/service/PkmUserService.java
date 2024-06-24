package com.example.service;

import com.example.entity.PkmUser;
import com.example.repository.PkmUserRepository;

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
public class PkmUserService {

    PkmUserRepository PkmUserRepository;

    @Autowired
    public PkmUserService(PkmUserRepository PkmUserRepository) {
        this.PkmUserRepository = PkmUserRepository;
    }

    public PkmUser register(PkmUser pkmUser) {
        String username = pkmUser.getUserName();

        boolean userExists = pkmUser.existsByUserName(username);
        if (userExists) {
            return null;
        }

        if (pkmUser.getUserName() == null || pkmUser.getUserName().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (pkmUser.getPassword() == null || pkmUser.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }

        return PkmUserRepository.save(pkmUser);
    }

    public Optional<PkmUser> login(PkmUser pkmUser) {
        String username = pkmUser.getUserName();
        String password = pkmUser.getPassword();

        PkmUser authPkmUser = PkmUserRepository.findByUserName(username);

        if (authPkmUser != null && authPkmUser.getPassword().equals(password)) {
            return Optional.of(authPkmUser);
        } else {
            return Optional.empty();
        }
    }

}