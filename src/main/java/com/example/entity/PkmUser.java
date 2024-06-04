package com.example.entity;

import javax.persistence.*;

/**
 * This is a class that models a PkmUser.
 *
 * You should NOT make any modifications to this class.
 */
@Entity
@Table(name = "pkm_users") // Specifies the table name
public class PkmUser {
    /**
     * An id for this PkmUser. You should use this as the Entity's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * A username for this PkmUser (must be unique and not blank)
     */
    @Column(name = "user_name", nullable = false, unique = true, length = 25)
    private String username;

    /**
     * A password for this PkmUser (must be over 4 characters)
     */
    @Column(name = "password", nullable = false, length = 30)
    private String password;

    /**
     * A default, no-args constructor, as well as correctly formatted getters and setters, are needed for
     * Jackson Objectmapper to work.
     */
    public PkmUser() {
    }

    /**
     * When posting a new PkmUser, the id can be generated by the database. In that case, a constructor without
     * id is needed.
     *
     * @param username
     * @param password
     */
    public PkmUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * When retrieving a PkmUser from the database, all fields will be needed. In that case, a constructor with all
     * fields is needed.
     *
     * @param id
     * @param username
     * @param password
     */
    public PkmUser(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
        PkmUser other = (PkmUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
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
        return "PkmUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}