package unchk.projects.lostnfound.controllers;

import java.util.List;  // Utiliser java.util.List

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.models.Message;
import unchk.projects.lostnfound.services.MessageService;

@RestController
@RequestMapping("/messages")
public class ChatController {

    @Autowired
    private MessageService messageService;

    // Endpoint pour récupérer les messages par annonce
    @GetMapping("/annonce/{annonceId}")
    public ResponseEntity<List<Message>> getMessagesByAnnonce(@PathVariable Long annonceId) {
        Annonces annonce = new Annonces();
        annonce.setId(annonceId);
        List<Message> messages = messageService.getMessagesByAnnonce(annonceId);
        return ResponseEntity.ok(messages);
    }
    
    
 
    
}
