package com.example.controller;

import com.example.entity.PkmUser;
import com.example.service.PkmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
    public class PkmUserController {
        
        private PkmUserService pkmUserService;

        @Autowired
    public PkmUserController(PkmUserService pkmUserService) {
        this.pkmUserService = pkmUserService;

    }

    @PostMapping(value = "/register")
    public ResponseEntity<PkmUser> register(@RequestBody PkmUser requestbody) {
    
        PkmUser newPkmUser = pkmUserService.register(requestbody);
        if(newPkmUser==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            return ResponseEntity.ok(newPkmUser);
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<PkmUser> loginHandler(@RequestBody PkmUser requestBody) {
        Optional<PkmUser> authenticatedAccount = pkmUserService.login(requestBody);
        if (authenticatedAccount.isPresent()) {
            return ResponseEntity.ok(authenticatedAccount.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
