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

    private PkmUserRepository pkmUserRepository;
 
    @Autowired
    public PkmUserService(PkmUserRepository pkmUserRepository ) {
        this.pkmUserRepository = pkmUserRepository;
    }

    public PkmUser register(PkmUser pkmUser) {
        String userName = pkmUser.getUserName();

        boolean userExists = pkmUserRepository.existsByUserName(userName);
        if (userExists) {
            return null;
        }

        if (pkmUser.getUserName() == null || pkmUser.getUserName().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (pkmUser.getPassword() == null || pkmUser.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        
        return pkmUserRepository.save(pkmUser);
    }

    public Optional<PkmUser> login(PkmUser pkmUser) {
        String userName = pkmUser.getUserName();
        String password = pkmUser.getPassword();

        PkmUser authPkmUser = pkmUserRepository.findByUsername(userName);

        if (authPkmUser != null && authPkmUser.getPassword().equals(password)) {
            return Optional.of(authPkmUser);
        } else {
            return Optional.empty();
        }
    }

}