package com.example.service;

import com.example.entity.PokemonMember;
import com.example.entity.PokemonTeam;
import com.example.repository.PokemonMemberRepository;
import com.example.repository.PokemonTeamRepository;
import com.example.repository.PkmUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PkmUserRepository pkmUserRepository;

    @Autowired
    private PokemonTeamRepository pokemonTeamRepository;

    @Autowired
    private PokemonMemberRepository pokemonMemberRepository;

    public PokemonTeam saveTeam(Integer userId, String teamName, PokemonMember pokemonMember) {
        PokemonTeam pokemonTeam = new PokemonTeam();
        pokemonTeam.setPkmUserId(userId);
        pokemonTeam.setTeamName(teamName);
        PokemonTeam savedTeam = pokemonTeamRepository.save(pokemonTeam);

        pokemonMember.setId(savedTeam.getId());
        pokemonMemberRepository.save(pokemonMember);

        savedTeam.setPokemonMember(pokemonMember);
        return savedTeam;
    }

    public List<PokemonTeam> getUserTeamNames(Integer userId) {
        return pokemonTeamRepository.findByPkmUserId(userId);
    }

    public Optional<PokemonMember> getTeam(Integer teamId) {
        return pokemonMemberRepository.findById(teamId);
    }

    public List<PokemonTeam> getAllUserTeams(Integer userId) {
        return pokemonTeamRepository.findByPkmUserId(userId);
    }

    public Optional<String> getTeamName(Integer teamId) {
        return pokemonTeamRepository.findById(teamId).map(PokemonTeam::getTeamName);
    }
}
