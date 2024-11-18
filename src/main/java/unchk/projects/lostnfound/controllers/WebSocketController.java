package unchk.projects.lostnfound.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.models.Message;
import unchk.projects.lostnfound.repos.MessageRepository;
import unchk.projects.lostnfound.services.MessageService;

@RestController
@Controller
@RequestMapping
public class WebSocketController {
  
	
	@Autowired
	private final MessageService messageService;
	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    public WebSocketController(MessageService messageService) {
        this.messageService = messageService;
       
    }
	

    
    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Message sendMessage( Message message) {
    	
    	
    	System.out.println("envoyé"+message);
    	
   
        messageService.saveMessage(message);
    	
    System.out.println("message"+ message);    
        return message; // Diffuser le message reçu à tous les clients connectés
    }
    
    // Récupérer les messages par utilisateur (sender ou receiver)
   
   
    
    
    // Récupération des messages pour l'utilisateur connecté (m1) et un autre utilisateur spécifique (userId)
    
    
    @GetMapping("/msg/{userId}/{userId2}/{annonceId}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(
            @PathVariable Long userId,
            @PathVariable Long userId2,
            @PathVariable Long annonceId
            ) {
    	
    	System.out.println("userid"+userId+"u2"+userId2+"ann:"+annonceId);
        
        List<Message> messages;
        
        // Récupérer les messages liés à l'annonce et aux utilisateurs spécifiés
        
            messages = messageService.getMessagesBetweenUsers(userId, userId2,annonceId);
            System.out.println("les messages trouvé:"+messages.size());

        return ResponseEntity.ok(messages);
    }
    
    
    // Nouvelle méthode pour récupérer tous les messages
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();

        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        System.out.println("les mess"+messages);

        return ResponseEntity.ok(messages);
    }
    
    
    
    
    
    
    
   
    /*
    
    @PostMapping("/test-save-message")
    public ResponseEntity<Message> testSaveMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }
*/
    
  
    
    


}
