package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Service
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // POST localhost:8080/messages
    public Message postMessage(Message message) {
        int account_id = message.getPosted_by();
        boolean userExists = accountRepository.existsById(account_id);
        if (message.getMessage_text() == null || 
            message.getMessage_text().trim().isEmpty() || 
            message.getMessage_text().length() > 225 ||
            !userExists) {
            throw new IllegalArgumentException("Invalid message or user");
        }
        return messageRepository.save(message);
    }
    

    // GET request on the endpoint GET localhost:8080/messages
    // As a user, I should be able to submit a GET request on the endpoint GET
    // localhost:8080/messages.
    // - The response body should contain a JSON representation of a list containing
    // all messages retrieved from the database. It is expected for the list to
    // simply be empty if there are no messages. The response status should always
    // be 200, which is the default.
    public List<Message> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    // GET request on the endpoint GET localhost:8080/messages/{message_id}
    // As a user, I should be able to submit a GET request on the endpoint GET
    // localhost:8080/messages/{message_id}.
    // - The response body should contain a JSON representation of the message
    // identified by the message_id. It is expected for the response body to simply
    // be empty if there is no such message. The response status should always be
    // 200, which is the default.
    public Optional<Message> getMessageById(int message_id) {
        // long longId = message_id;
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        return optionalMessage;

    }

    // public Grocery getGroceryById(long id){
    // //findById returns a type Optional<Grocery>. This helps the developer avoid
    // null pointer
    // //exceptions. We can use the method .get() to convert an Optional<Grocery> to
    // Grocery.
    // Optional<Grocery> optionalGrocery = groceryRepository.findById(id);
    // if(optionalGrocery.isPresent()){
    // return optionalGrocery.get();
    // }else{
    // return null;
    // }
    // }

    // ` DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
    // As a User, I should be able to submit a DELETE request on the endpoint DELETE
    // localhost:8080/messages/{message_id}.
    // - The deletion of an existing message should remove an existing message from
    // the database. If the message existed, the response body should contain the
    // number of rows updated (1). The response status should be 200, which is the
    // default.
    // - If the message did not exist, the response status should be 200, but the
    // response body should be empty. This is because the DELETE verb is intended to
    // be idempotent, ie, multiple calls to the DELETE endpoint should respond with
    // the same type of response.

    public int deleteMessage(int message_id) {
        // long longId = message_id;
        boolean exists = messageRepository.existsById(message_id);
        if(exists){
           messageRepository.deleteById(message_id);
           return 1;
        }else{
            return 0;
        }

    }

    //     ## 7: Our API should be able to update a message text identified by a message ID.
// As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}. The request body should contain a new message_text values to replace the message identified by message_id. The request body can not be guaranteed to contain any other information.
// - The update of a message should be successful if and only if the message id already exists and the new message_text is not blank and is not over 255 characters. If the update is successful, the response body should contain the number of rows updated (1), and the response status should be 200, which is the default. The message existing on the database should have the updated message_text.
// - If the update of the message is not successful for any reason, the response status should be 400. (Client error)
// PATCH localhost:8080/messages/{message_id}
public int patchMessage(Message message, int messageId) {
    Optional<Message> optionalExistingMessage = messageRepository.findById(messageId);
    if (optionalExistingMessage.isPresent()) {
        Message existingMessage = optionalExistingMessage.get();
        if (message.getMessage_text() != null && !message.getMessage_text().trim().isEmpty()) {
            existingMessage.setMessage_text(message.getMessage_text());
        }
        Message updatedMessage = messageRepository.save(existingMessage);
        return updatedMessage.equals(existingMessage) ? 0 : 1;
    }
    return 0;
}

}
