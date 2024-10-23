package unchk.projects.lostnfound.services;

import org.springframework.stereotype.Service;


import unchk.projects.lostnfound.models.Message;
import unchk.projects.lostnfound.repos.MessageRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Message sendMessage(Message message) {
    	   	
    	
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByReceiverId(Long receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }

    public List<Message> getMessagesBySenderId(Long senderId) {
        return messageRepository.findBySenderId(senderId);
    }
    
    // Récupérer les messages envoyés ou reçus par un utilisateur
    public List<Message> getMessagesByUser(Long userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId);
    }
    
    // Récupérer les messages par annonce
    public List<Message> findMessagesByAnnonceId(Long annonceId) {
        return messageRepository.findByAnnonceId(annonceId);
    }
    
   
    public Message saveMessage(Message message) {
       
       messageRepository.save(message);
      
        
		return message;
    }
    
    
   

    
    }




