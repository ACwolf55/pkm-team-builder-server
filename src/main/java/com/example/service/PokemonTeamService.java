package com.example.service;


import com.example.entity.PokemonTeam;
import com.example.repository.PokemonTeamRepository;
import com.example.entity.Trainer;
import com.example.repository.TrainerRepository;
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
    private TrainerRepository pkmUserRepository;
 
    @Autowired
    public PokemonTeamService(PokemonTeamRepository pokemonTeamRepository, TrainerRepository pkmUserRepository ) {
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


    public List<PokemonTeam> getAllUserTeams(int pkm_user_id) {
        // List<Message> userMessages = new ArrayList<>();
        // List<Message> messages = getAllMessages();
        // for (Message message : messages) {
        //     if (message.getPosted_by() == account_id) {
        //         userMessages.add(message);
        //     }
        // }
           return pokemonTeamRepository.findByPkmUserId(pkm_user_id);
    }

    public int deletePokemonTeam(int pokemon_team_id) {
        // long longId = message_id;
        boolean exists = pokemonTeamRepository.existsById(pokemon_team_id);
        if (exists) {
            pokemonTeamRepository.deleteById(pokemon_team_id);
            return 1;
        } else {
            return 0;
        }
    }

    public Optional<PokemonTeam> getPokemonTeamById(int pokemon_team_id) {
        Optional<PokemonTeam> optionalPokemonTeam = pokemonTeamRepository.findById(pokemon_team_id);
        return optionalPokemonTeam;

    }




}
