package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "pkm_users")
public class PkmUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pkm_user_id")
    private Integer pkmUserId;

    @Column(name = "user_name", nullable = false, unique = true, length = 25)
    private String userName;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    // Default constructor
    public PkmUser() {
    }

    // Constructor with all fields except the primary key
    public PkmUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getters and setters
    public Integer getPkmUserId() {
        return pkmUserId;
    }

    public void setPkmUserId(Integer pkmUserId) {
        this.pkmUserId = pkmUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "PkmUser{" +
                "pkmUserId=" + pkmUserId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
