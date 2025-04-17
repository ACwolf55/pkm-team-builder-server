package com.example.controller;

import com.example.entity.PokemonTeam;
import com.example.service.PokemonTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<PokemonTeam> postMessages(@RequestBody PokemonTeam requestBody) {
        try {
            PokemonTeam newPokemonTeam = pokemonTeamService.postPokemonTeam(requestBody);
            return ResponseEntity.ok(newPokemonTeam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pokemon_team/team_id/{pokemon_team_id}")
    public ResponseEntity<PokemonTeam> getPokemonTeam(@PathVariable("pokemon_team_id") int pokemon_team_id) {
        Optional<PokemonTeam> optionalPokemonTeam = pokemonTeamService.getPokemonTeamById(pokemon_team_id);
        if (optionalPokemonTeam.isPresent()) {
            return ResponseEntity.ok(optionalPokemonTeam.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/pokemon_team/{pokemon_team_id}")
    public ResponseEntity<Integer> deletePokemonTeam(@PathVariable("pokemon_team_id") int pokemon_team_id) {
        int rowsUpdated = pokemonTeamService.deletePokemonTeam(pokemon_team_id);
        if (rowsUpdated > 0) {
            return ResponseEntity.ok(rowsUpdated);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/pokemon_team/{trainer_id}")
    public ResponseEntity<List<PokemonTeam>> getAllUserTeamsHandler(@PathVariable("trainer_id") int trainer_id) {
        List<PokemonTeam> userPokemonTeams = pokemonTeamService.getAllUserTeams(trainer_id);
        return ResponseEntity.ok(userPokemonTeams);
    }
}
