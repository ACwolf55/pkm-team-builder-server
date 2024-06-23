package com.example.controller;

import com.example.entity.PokemonTeam;
import com.example.service.PokemonTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon-teams")
public class PokemonTeamController {

    private final PokemonTeamService pokemonTeamService;

    @Autowired
    public PokemonTeamController(PokemonTeamService pokemonTeamService) {
        this.pokemonTeamService = pokemonTeamService;
    }


    @PostMapping("/pokemon_team")
    public ResponseEntity<PokemonTeam> postMessagesHandler(@RequestBody PokemonTeam requestBody) {
        try {
            PokemonTeam newPokemonTeam = pokemonTeamService.postTeam(requestBody);
            return ResponseEntity.ok(newPokemonTeam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pokemon_team/{pokemon_team_id}")
    public ResponseEntity<PokemonTeam> getPokemonTeam(@PathVariable("pokemon_team_id") int pokemon_team_id) {
        Optional<PokemonTeam> optionalPokemonTeam = pokemonTeamService.getPokemonTeamById(pokemon_team_id);
        if (optionalPokemonTeam.isPresent()) {
            return ResponseEntity.ok(optionalPokemonTeam.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/pokemon_team/{pokemon_team_id}")
    public ResponseEntity<Integer> deleteAccountMessagesHandler(@PathVariable("pokemon_team_id") int pokemon_team_id) {
        int rowsUpdated = pokemonTeamService.deletePokemonTeam(pokemon_team_id);
        if(rowsUpdated>0){
        return ResponseEntity.ok(rowsUpdated);
        }else{
            return ResponseEntity.ok().build();
        }
    }
}
