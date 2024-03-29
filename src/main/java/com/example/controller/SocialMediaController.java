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
    public ResponseEntity<Account> register(@RequestBody Account requestbody) {
    
        Account newAccount = accountService.register(requestbody);
        if(newAccount==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            return ResponseEntity.ok(newAccount);
        }
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
    public ResponseEntity<Account> loginHandler(@RequestBody Account requestBody) {
        Optional<Account> authenticatedAccount = accountService.login(requestBody);
        if (authenticatedAccount.isPresent()) {
            return ResponseEntity.ok(authenticatedAccount.get());
        } else {
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
        try {
            Message newMessage = messageService.postMessage(requestBody);
            return ResponseEntity.ok(newMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<Message> getSingleMessageHandler(@PathVariable("message_id") int message_id) {
        Optional<Message> optionalMessage = messageService.getMessageById(message_id);
        if (optionalMessage.isPresent()) {
            return ResponseEntity.ok(optionalMessage.get());
        } else {
            return ResponseEntity.ok().build();
        }
    }
    

    // ` DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
    // As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}.
    // - The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the number of rows updated (1). The response status should be 200, which is the default.
    // - If the message did not exist, the response status should be 200, but the response body should be empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteAccountMessagesHandler(@PathVariable("message_id") int message_id) {
        int rowsUpdated = messageService.deleteMessage(message_id);
        if(rowsUpdated>0){
        return ResponseEntity.ok(rowsUpdated);
        }else{
            return ResponseEntity.ok().build();
        }
    }

//     ## 7: Our API should be able to update a message text identified by a message ID.
// As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}. The request body should contain a new message_text values to replace the message identified by message_id. The request body can not be guaranteed to contain any other information.
// - The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters. If the update is successful, the response body should contain the number of rows updated (1), and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
// - If the update of the message is not successful for any reason, the response status should be 400. (Client error)
// PATCH localhost:8080/messages/{message_id}
@PatchMapping("/messages/{message_id}")
public ResponseEntity<Integer> updateMessageHandler(@RequestBody Message requestBody, @PathVariable("message_id") int messageId) {
    int rowsUpdated = messageService.patchMessage(requestBody, messageId);
    if (rowsUpdated > 0) {
        return ResponseEntity.ok(rowsUpdated);
    } else {
        return ResponseEntity.badRequest().build();
    }
}


// ## 8: Our API should be able to retrieve all messages written by a particular user.
// As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{account_id}/messages.
// - The response bo@GetMapping("/accounts/{account_id}/messages")
//     public ResponseEntity<List<Message>> getAllUserMessagesHandler(@PathVariable("account_id") int account_id) {
//         List<Message> messages = messageService.getAllUserMessages();
//         return ResponseEntity.ok(messages);
//     }dy should contain a JSON representation of a list containing all messages posted by a particular user, which is retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.
// 
// GET localhost:8080/accounts/{account_id}/messages
@GetMapping("/accounts/{account_id}/messages")
public ResponseEntity<List<Message>> getAllUserMessagesHandler(@PathVariable("account_id") int account_id) {
    List<Message> messages = messageService.getAllUserMessages(account_id);
    return ResponseEntity.ok(messages);
        }

}
