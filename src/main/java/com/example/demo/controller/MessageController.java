package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MessageModel;
import com.example.demo.utils.UserStorage;

@RestController
public class MessageController {

	@Autowired
	private SimpMessagingTemplate template;

	@GetMapping("/chat/{id}")
	public void sendMessage(@DestinationVariable String id, MessageModel message) {
		boolean isExists = UserStorage.getInstance().isExistsUser(id);
		if (isExists) {
			template.convertAndSend("/topic/messages/" + id, message);
		}
	}
}
