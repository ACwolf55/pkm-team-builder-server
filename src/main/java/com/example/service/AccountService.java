package com.example.service;


import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Optional;


import java.util.List;

@Service
@Transactional
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account register(Account account){
        if (account.getUsername() == null || account.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long");
        }
        // if (existsByUsername(account.getUsername())) {
        //     throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        // }
        return accountRepository.save(account);
    }

    public boolean existsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    public Account login(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();


        Account authAccount = accountRepository.findByUsername(username);
         // Check if account exists
        //  if (authAccount == null) {
        //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        // }

        // Check if password is correct
        // if (!authAccount.getPassword().equals(password)) {
        //     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        // }
        return authAccount; 
    }

    // public List<Album> getAllAlbums(){
    //     return albumRepository.findAll();
    // }
}
