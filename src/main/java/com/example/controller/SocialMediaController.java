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
import java.util.Optional;


import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
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
    public Account register(@RequestBody Account requestbody) {
        // you will need to change the method's parameters and return the extracted
        // request body.
        return accountService.register(requestbody);
    }

    // POST localhost:8080/login
    // As a user, I should be able to verify my login on the endpoint POST
    // localhost:8080/login. The request body will contain a JSON representation of
    // an Account.
    // - The login will be successful if and only if the username and password
    // provided in the request body JSON match a real account existing on the
    // database. If successful, the response body should contain a JSON of the
    // account in the response body, including its account_id. The response status
    // should be 200 OK, which is the default.
    // - If the login is not successful, the response status should be 401.
    // (Unauthorized)
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
    // As a user, I should be able to submit a new post on the endpoint POST
    // localhost:8080/messages. The request body will contain a JSON representation
    // of a message, which should be persisted to the database, but will not contain
    // a message_id.
    // - The creation of the message will be successful if and only if the
    // message_text is not blank, is not over 255 characters, and posted_by refers
    // to a real, existing user. If successful, the response body should contain a
    // JSON of the message, including its message_id. The response status should be
    // 200, which is the default. The new message should be persisted to the
    // database.
    // - If the creation of the message is not successful, the response status
    // should be 400. (Client error)
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessagesHandler(@RequestBody Message requestBody) {
        Message newMessage = messageService.postMessage(requestBody);
        return ResponseEntity.ok(newMessage);
    }

    // GET request on the endpoint GET localhost:8080/messages
    // As a user, I should be able to submit a GET request on the endpoint GET
    // localhost:8080/messages.
    // - The response body should contain a JSON representation of a list containing
    // all messages retrieved from the database. It is expected for the list to
    // simply be empty if there are no messages. The response status should always
    // be 200, which is the default.
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessagesHandler() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    // GET request on the endpoint GET localhost:8080/messages/{message_id}
    // As a user, I should be able to submit a GET request on the endpoint GET
    // localhost:8080/messages/{message_id}.
    // - The response body should contain a JSON representation of the message
    // identified by the message_id. It is expected for the response body to simply
    // be empty if there is no such message. The response status should always be
    // 200, which is the default.
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getSingleMessageHandler(@PathVariable("message_id") int messageId) {
        Optional<Message> optionalMessage = messageService.getMessageById(messageId);
        if (optionalMessage.isPresent()) {
            return ResponseEntity.ok(optionalMessage.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    // ` DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
    @DeleteMapping("/accounts/{account_id}/messages")
    public ResponseEntity<String> deleteAccountMessagesHandler(@PathVariable("account_id") String accountId) {
        return ResponseEntity.ok("Messages for account with id " + accountId + " deleted successfully");
    }

}
