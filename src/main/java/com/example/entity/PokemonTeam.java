package com.example.entity;

import javax.persistence.*;

/**
 * This is a class that models a PokemonTeam.
 *
 * You should NOT make any modifications to this class.
 */
@Entity
@Table(name = "pokemon_team") // Specifies the table name
public class PokemonTeam {
    /**
     * An id for this PokemonTeam. You should use this as the Entity's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The user this team belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "pkm_user_id", nullable = false)
    private PkmUser pkmUser;

    /**
     * The name of the team.
     */
    @Column(name = "team_name", nullable = false)
    private String teamName;

    /**
     * A default, no-args constructor, as well as correctly formatted getters and setters, are needed for
     * Jackson Objectmapper to work.
     */
    public PokemonTeam() {
    }

    /**
     * Constructor for creating a new PokemonTeam without id.
     *
     * @param pkmUser
     * @param teamName
     */
    public PokemonTeam(PkmUser pkmUser, String teamName) {
        this.pkmUser = pkmUser;
        this.teamName = teamName;
    }

    /**
     * Constructor for creating a new PokemonTeam with id.
     *
     * @param id
     * @param pkmUser
     * @param teamName
     */
    public PokemonTeam(Integer id, PkmUser pkmUser, String teamName) {
        this.id = id;
        this.pkmUser = pkmUser;
        this.teamName = teamName;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return pkmUser
     */
    public PkmUser getPkmUser() {
        return pkmUser;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param pkmUser
     */
    public void setPkmUser(PkmUser pkmUser) {
        this.pkmUser = pkmUser;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Overriding the default equals() method adds functionality to tell when two objects are identical, allowing
     * Assert.assertEquals and List.contains to function.
     *
     * @param obj the other object.
     * @return true if obj is equal to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PokemonTeam other = (PokemonTeam) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (teamName == null) {
            if (other.teamName != null)
                return false;
        } else if (!teamName.equals(other.teamName))
            return false;
        if (pkmUser == null) {
            if (other.pkmUser != null)
                return false;
        } else if (!pkmUser.equals(other.pkmUser))
            return false;
        return true;
    }

    /**
     * Overriding the default toString() method allows for easy debugging.
     *
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return "PokemonTeam{" +
                "id=" + id +
                ", pkmUser=" + pkmUser +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
