package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;
import java.util.*;

@Service
public class MessageService {
  private MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message createMsg(Message newMessage) {
    String messageText = newMessage.getMessageText();
    int messageLength = messageText.length();

    if((messageText != "") && (messageLength <= 255)) {
      return messageRepository.save(newMessage);
    }
    return null;
  }

  public List<Message> getAllMsgs() {
    return messageRepository.findAll();
  }

  public Message getMsgById(Integer messageId) {
    Optional<Message> optionalMessage = messageRepository.findById(messageId);

    if(optionalMessage.isPresent()) {
      Message retrievedMessage = optionalMessage.get();
      return retrievedMessage;
    }
    return null;
  }

  public Message deleteMsgById(Integer messageId) {
    Message verifiedMessage = getMsgById(messageId);

    if(verifiedMessage != null) {
      messageRepository.deleteById(messageId);
      return verifiedMessage;
    }
    return null;
  }

  public Message updateMsg(Integer messageId, Message updatedTextMessage) {

    return null;
  }

  public List<Message> getAllMsgsByActId(Integer accountId) {

    return null;
  }
}
