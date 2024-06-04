package com.example.repository;

import com.example.entity.PokemonTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTeamRepository extends JpaRepository<PokemonTeam, Integer> {
    // Additional query methods can be defined here if needed
}
