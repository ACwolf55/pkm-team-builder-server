package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Integer trainerId;

    @Column(name = "trainer_name", nullable = false, unique = true, length = 25)
    private String trainerName;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    // Default constructor
    public Trainer() {
    }

    // Constructor with all fields except the primary key
    public Trainer(String trainerName, String password) {
        this.trainerName = trainerName;
        this.password = password;
    }

    // Getters and setters
    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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
        return "Trainer{" +
                "trainerId=" + trainerId +
                ", trainerName='" + trainerName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
