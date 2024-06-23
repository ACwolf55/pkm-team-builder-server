package com.example.service;


import com.example.entity.PokemonTeam;
import com.example.repository.PokemonTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class PokemonTeamService {
    
    private final PokemonTeamRepository pokemonTeamRepository;
 
    @Autowired
    public PokemonTeamService(PokemonTeamRepository pokemonTeamRepository ) {
        this.pokemonTeamRepository = pokemonTeamRepository;
        
    }

     
     public PokemonTeam postMessage(PokemonTeam pokemonTeam) {
        int account_id = PokemonTeam.getPosted_by();
        boolean userExists = PokemonTeamRepository.existsById(account_id);
        if (message.getMessage_text() == null ||
                message.getMessage_text().trim().isEmpty() ||
                message.getMessage_text().length() > 225 ||
                !userExists) {
            throw new IllegalArgumentException("Invalid message or user");
        }
        return messageRepository.save(message);
    }




}
