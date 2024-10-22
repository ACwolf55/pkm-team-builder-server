package com.example.repository;

import com.example.entity.PokemonTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonTeamRepository extends JpaRepository<PokemonTeam, Integer> {
    List<PokemonTeam> findByTrainerId(Integer trainerId);
    
}
