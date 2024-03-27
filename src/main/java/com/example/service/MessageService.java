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
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            throw new IllegalArgumentException("A message cannot be empty!");
        }

        return messageRepository.save(message);
    }

   // GET request on the endpoint GET localhost:8080/messages
//    As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.
// - The response body should contain a JSON representation of a list containing all messages retrieved from the database. It is expected for the list to simply be empty if there are no messages. The response status should always be 200, which is the default.
public List<Message> getAllMessages() {
    List<Message> messages = messageRepository.findAll();
    return messages;
}

   // GET request on the endpoint GET localhost:8080/messages/{message_id}
    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new IllegalArgumentException("The message you seek remains obscured from view."));
    }

    // ` DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
//     As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}.
// - The deletion of an existing message should remove an existing message from the database. If the message existed, the response body should contain the number of rows updated (1). The response status should be 200, which is the default.
// - If the message did not exist, the response status should be 200, but the response body should be empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE endpoint should respond with the same type of response.

public int deleteMessage(Long messageId) {
    Optional<Message> messageToDelete = messageRepository.findById(messageId);

    if (messageToDelete.isPresent()) {
        messageRepository.delete(messageToDelete.get());
        return 1; // Return 1 for successful deletion
    } else {
        return 0; // Return null response body
    }
    }
}
