package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.*;
import com.example.entity.*;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
public class SocialMediaController {
  private AccountService accountService;
  private MessageService messageService;

  @Autowired
  public SocialMediaController(AccountService accountService, MessageService messageService) {
    this.accountService = accountService;
    this.messageService = messageService;
  }

  @PostMapping("/register")
  public ResponseEntity<Account> registrationHandler(@RequestBody Account account) {
    Account duplicateAccount = accountService.verifyAccountUserExists(account);

    if(duplicateAccount == null) {
      Account registeredAccount = accountService.registerAccount(account);

      if(registeredAccount != null) {
        return ResponseEntity.status(200).body(registeredAccount);
      } else {
        return ResponseEntity.status(400).build();
      }
    }
    return ResponseEntity.status(409).build();
  }

  @PostMapping("/login")
  public ResponseEntity<Account> loginHandler(@RequestBody Account account) {
    Account verifiedAccount = accountService.verifyLogin(account);
    
    if(verifiedAccount != null) {
      return ResponseEntity.status(200).body(verifiedAccount);
    }
    return ResponseEntity.status(401).build();
  }

  @PostMapping("/messages")
  public ResponseEntity<Message> msgCreationHandler(@RequestBody Message message) {
    Account existingAccount = accountService.verifyAccountIdExists(message.getPostedBy());

    if(existingAccount != null) {
      Message createdMessage = messageService.createMsg(message);

      if(createdMessage != null) {
        return ResponseEntity.status(200).body(createdMessage);
      }
    }
    return ResponseEntity.status(400).build();
  }

  @GetMapping("/messages")
  public ResponseEntity<List<Message>> allMsgsHandler() {
    List<Message> allMessages = messageService.getAllMsgs();
    return ResponseEntity.status(200).body(allMessages);
  }

  @GetMapping("messages/{messageId}")
  public ResponseEntity<Message> msgByIdHandler(@PathVariable Integer messageId) {
    Message retrievedMessage = messageService.getMsgById(messageId);

    if(retrievedMessage != null) {
      return ResponseEntity.status(200).body(retrievedMessage);
    }
    return ResponseEntity.status(200).build();
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity<Integer> deleteMsgByIdHandler(@PathVariable Integer messageId) {
    Message retrievedMessage = messageService.deleteMsgById(messageId);
    
    if(retrievedMessage != null) {
      return ResponseEntity.status(200).body(1);
    }
    return ResponseEntity.status(200).build();
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity<Integer> updateMsgHandler(@PathVariable Integer messageId, @RequestBody Message message) {
    Message retrievedMessage = messageService.updateMsg(messageId, message);

    if(retrievedMessage != null) {
      return ResponseEntity.status(200).body(1);
    }
    return ResponseEntity.status(400).build();
  }

  @GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> allMsgsByActIdHandler(@PathVariable Integer accountId) {
    List<Message> allMessages = messageService.getAllMsgsByActId(accountId);
    return ResponseEntity.status(200).body(allMessages);
  }
}
