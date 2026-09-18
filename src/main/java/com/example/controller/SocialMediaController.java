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
    Account duplicateAccount = accountService.verifyAccountExists(account);

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

    return ResponseEntity.status(200).body(null);
  }

  @GetMapping("/messages")
  public ResponseEntity<List<Message>> allMsgsHandler() {
    
    return ResponseEntity.status(200).body(null);
  }

  @GetMapping("messages/{messageId}")
  public ResponseEntity<Message> msgByIdHandler(@PathVariable Integer messageId) {

    return ResponseEntity.status(200).body(null);
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity<Message> deleteMsgByIdHandler(@PathVariable Integer messageId) {

    return ResponseEntity.status(200).body(null);
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity<Message> updateMsgHandler(@PathVariable Integer messageId, @RequestBody Message message) {

    return ResponseEntity.status(200).body(null);
  }

  @GetMapping("/accounts/{accountId/messages}")
  public ResponseEntity<List<Message>> allMsgsByActIdHandler(@PathVariable Integer accountId) {
    
    return ResponseEntity.status(200).body(null);
  }

}
