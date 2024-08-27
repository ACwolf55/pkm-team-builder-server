package com.example.controller;

import com.example.entity.Trainer;
import com.example.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
    public class TrainerController {
        
        private TrainerService trainerService;

        @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;

    }

    @PostMapping(value = "/register")
    public ResponseEntity<Trainer> register(@RequestBody Trainer requestbody) {
    
        Trainer newTrainer = trainerService.register(requestbody);
        if(newTrainer==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            return ResponseEntity.ok(newTrainer);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Trainer> loginHandler(@RequestBody Trainer requestBody) {
        Optional<Trainer> authenticatedAccount = trainerService.login(requestBody);
        if (authenticatedAccount.isPresent()) {
            return ResponseEntity.ok(authenticatedAccount.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
