package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "pokemon_team")
public class PokemonTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_team_id")
    private Integer pokemonTeamId;

    @Column(name = "team_name", nullable = false, length = 50)
    private String teamName;

    @Column(name = "pokemon_1", nullable = false, length = 50)
    private String pokemon1;

    @Column(name = "pokemon_2", nullable = false, length = 50)
    private String pokemon2;

    @Column(name = "pokemon_3", nullable = false, length = 50)
    private String pokemon3;

    @Column(name = "pokemon_4", nullable = false, length = 50)
    private String pokemon4;

    @Column(name = "pokemon_5", nullable = false, length = 50)
    private String pokemon5;

    @Column(name = "pokemon_6", nullable = false, length = 50)
    private String pokemon6;

    @ManyToOne
    @JoinColumn(name = "pkm_user_id", nullable = false)
    private PkmUser pkmUser;

    // Default constructor
    public PokemonTeam() {
    }

    // Constructor with all fields
    public PokemonTeam(String teamName, String pokemon1, String pokemon2, String pokemon3, String pokemon4, String pokemon5, String pokemon6, PkmUser pkmUser) {
        this.teamName = teamName;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.pokemon3 = pokemon3;
        this.pokemon4 = pokemon4;
        this.pokemon5 = pokemon5;
        this.pokemon6 = pokemon6;
        this.pkmUser = pkmUser;
    }

    // Getters and setters
    public Integer getPokemonTeamId() {
        return pokemonTeamId;
    }

    public void setPokemonTeamId(Integer pokemonTeamId) {
        this.pokemonTeamId = pokemonTeamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(String pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public String getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(String pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public String getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(String pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public String getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(String pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public String getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(String pokemon5) {
        this.pokemon5 = pokemon5;
    }

    public String getPokemon6() {
        return pokemon6;
    }

    public void setPokemon6(String pokemon6) {
        this.pokemon6 = pokemon6;
    }

    public PkmUser getPkmUser() {
        return pkmUser;
    }

    public void setPkmUser(PkmUser pkmUser) {
        this.pkmUser = pkmUser;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "PokemonTeam{" +
                "pokemonTeamId=" + pokemonTeamId +
                ", teamName='" + teamName + '\'' +
                ", pokemon1='" + pokemon1 + '\'' +
                ", pokemon2='" + pokemon2 + '\'' +
                ", pokemon3='" + pokemon3 + '\'' +
                ", pokemon4='" + pokemon4 + '\'' +
                ", pokemon5='" + pokemon5 + '\'' +
                ", pokemon6='" + pokemon6 + '\'' +
                ", pkmUser=" + pkmUser +
                '}';
    }
}
