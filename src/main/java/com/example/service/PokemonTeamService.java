package com.example.service;


import com.example.entity.PokemonTeam;
import com.example.repository.PokemonTeamRepository;
import com.example.entity.PkmUser;
import com.example.repository.PkmUserRepository;
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
    private PkmUserRepository pkmUserRepository;
 
    @Autowired
    public PokemonTeamService(PokemonTeamRepository pokemonTeamRepository, PkmUserRepository pkmUserRepository ) {
        this.pokemonTeamRepository = pokemonTeamRepository;
        this.pkmUserRepository = pkmUserRepository;
        
    }

     
     public PokemonTeam postPokemonTeam(PokemonTeam pokemonTeam) {
        int pkmUserId = pokemonTeam.getPkmUserId();
        boolean pokemonTeamExists = pkmUserRepository.existsById(pkmUserId);
        if (pokemonTeam.getPokemon1() == null ||
                pokemonTeam.getPokemon1().trim().isEmpty() ||
                pokemonTeam.getPokemon1().length() > 50 ||
                pokemonTeam.getTeamName() == null ||
                pokemonTeam.getTeamName().trim().isEmpty() ||
                pokemonTeam.getTeamName().length() > 50 ||
                !pokemonTeamExists) {
            throw new IllegalArgumentException("Invalid team name or team doesnt have a pokemon");
        }
        return pokemonTeamRepository.save(pokemonTeam);
    }




}
