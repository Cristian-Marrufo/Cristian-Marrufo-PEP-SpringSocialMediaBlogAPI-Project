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

    return null;
  }

  public List<Message> getAllMsgs() {

    return null;
  }

  public Message getMsgById(Integer messageId) {

    return null;
  }

  public Message deleteMsgById(Integer messageId) {

    return null;
  }

  public Message updateMsg(Integer messageId, Message updatedTextMessage) {

    return null;
  }

  public List<Message> getAllMsgsByActId(Integer accountId) {

    return null;
  }
}
