package unchk.projects.lostnfound.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unchk.projects.lostnfound.models.Message;
import unchk.projects.lostnfound.repos.MessageRepository;
import unchk.projects.lostnfound.services.MessageService;

@RestController
@Controller
@RequestMapping
public class WebSocketController {
  
	
	
	private final MessageService messageService;
	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
       
    }
	


    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
   
        messageService.saveMessage(message);
    	
        return message; // Diffuser le message reçu à tous les clients connectés
    }
    
    // Récupérer les messages par utilisateur (sender ou receiver)
   
    
    @GetMapping("msg/{userId}")
    public List<Message> getMessagesByUser(@PathVariable ("userId") Long userId) {
        return messageService.getMessagesByUser(userId);
    }
    
    
    
    
   
    /*
    
    @PostMapping("/test-save-message")
    public ResponseEntity<Message> testSaveMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
*/
    
  
    
    


}
