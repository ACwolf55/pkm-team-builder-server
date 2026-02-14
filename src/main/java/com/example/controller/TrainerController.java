package com.example.controller;

import com.example.entity.Trainer;
import com.example.service.TrainerService;
import com.example.security.JwtUtil; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import java.util.HashMap;  
import java.util.Map;      
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth") 
public class TrainerController {
    
    private TrainerService trainerService;
    private JwtUtil jwtUtil;  // ← Add this

    @Autowired
    public TrainerController(TrainerService trainerService, JwtUtil jwtUtil) {  // ← Add jwtUtil parameter
        this.trainerService = trainerService;
        this.jwtUtil = jwtUtil;  // ← Add this
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
    public ResponseEntity<?> loginHandler(@RequestBody Trainer requestBody) {  // ← Changed return type to <?>
        Optional<Trainer> authenticatedAccount = trainerService.login(requestBody);
        
        if (authenticatedAccount.isPresent()) {
            Trainer trainer = authenticatedAccount.get();
            
            // Generate JWT token
            String token = jwtUtil.generateToken(trainer.getTrainerName());
            
            // Create response with token and trainer info
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("trainerId", trainer.getTrainerId());
            response.put("trainerName", trainer.getTrainerName());
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}