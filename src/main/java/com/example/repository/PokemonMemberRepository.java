package com.example.repository;

import com.example.entity.PokemonMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonMemberRepository extends JpaRepository<PokemonMember, Integer> {
    // Additional query methods can be defined here if needed
}
