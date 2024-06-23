package com.example.controller;

import com.example.entity.PokemonMember;
import com.example.entity.PokemonTeam;
import com.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    @Autowired
    private PokemonService PokemonService;

    @PostMapping("/team")
    public ResponseEntity<?> saveTeam(HttpSession session, @RequestBody PokemonTeamRequest request) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(403).body("Need to log in to save team!");
        }

        Integer userId = ((User) session.getAttribute("user")).getId();
        PokemonMember pokemonMember = new PokemonMember(request.getPokemon1(), request.getPokemon2(), request.getPokemon3(),
                request.getPokemon4(), request.getPokemon5(), request.getPokemon6());
        PokemonTeam savedTeam = pokemonService.saveTeam(userId, request.getTeamName(), pokemonMember);

        return ResponseEntity.status(201).body(savedTeam);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<PokemonTeam>> userTeamNames(HttpSession session) {
        Integer userId = ((User) session.getAttribute("user")).getId();
        List<PokemonTeam> teamNames = pokemonService.getUserTeamNames(userId);
        return ResponseEntity.status(201).body(teamNames);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<Optional<PokemonMember>> getTeam(@PathVariable Integer teamId) {
        Optional<PokemonMember> team = pokemonService.getTeam(teamId);
        return ResponseEntity.status(201).body(team);
    }

    @GetMapping("/all-teams")
    public ResponseEntity<List<PokemonTeam>> getAllUserTeams(HttpSession session) {
        Integer userId = ((User) session.getAttribute("user")).getId();
        List<PokemonTeam> allUserTeams = pokemonService.getAllUserTeams(userId);
        return ResponseEntity.status(201).body(allUserTeams);
    }

    @GetMapping("/team-name/{teamId}")
    public ResponseEntity<Optional<String>> getTeamName(@PathVariable Integer teamId) {
        Optional<String> teamName = pokemonService.getTeamName(teamId);
        return ResponseEntity.status(201).body(teamName);
    }
}
