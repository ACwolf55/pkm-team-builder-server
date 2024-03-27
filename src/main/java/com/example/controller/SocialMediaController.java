package com.example.controller;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;







/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;
   
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // POST localhost:8080/register
    @PostMapping(value = "/register")
    public Account register(@RequestBody Account requestbody){
        //you will need to change the method's parameters and return the extracted request body.
        return accountService.register(requestbody);
    }

    // POST localhost:8080/login
// As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account.
// - The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, which is the default.
// - If the login is not successful, the response status should be 401. (Unauthorized)
    @PostMapping(value = "/login")
    public ResponseEntity<Account> loginHandler(@RequestBody Account requestbody) {
        Account authenticatedAccount = accountService.login(requestbody);
        
        if (authenticatedAccount != null) {
            // Login successful
            return ResponseEntity.ok(authenticatedAccount);
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // POST localhost:8080/messages
    @PostMapping("/messages")
    public ResponseEntity<String> postMessagesHandler(@RequestBody String requestBody) {
        // Implement post messages logic
        return ResponseEntity.ok("Message posted successfully");
    }

    // GET request on the endpoint GET localhost:8080/messages
    @GetMapping("/messages")
    public ResponseEntity<String> getAllMessagesHandler() {
        // Implement get all messages logic
        return ResponseEntity.ok("List of all messages");
    }
    
    // GET request on the endpoint GET localhost:8080/messages/{message_id}
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<String> getSingleMessageHandler(@PathVariable("message_id") String messageId) {
        // Implement get single message logic
        return ResponseEntity.ok("Message with id " + messageId);
    }

    // ` DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
        @GetMapping("/accounts/{account_id}/messages")
        public ResponseEntity<String> getAccountMessagesHandler(@PathVariable("account_id") String accountId) {
        // Implement get account messages logic
        return ResponseEntity.ok("Messages for account with id " + accountId);
}





}
