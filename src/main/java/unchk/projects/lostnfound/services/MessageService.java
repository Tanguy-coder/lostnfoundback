package unchk.projects.lostnfound.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.models.Message;
import unchk.projects.lostnfound.repos.MessageRepository;
import unchk.projects.lostnfound.repos.AnnonceRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AnnonceRepository annonceRepository; // Injection du repository pour les annonces

    @Autowired
    public MessageService(MessageRepository messageRepository, AnnonceRepository annonceRepository) {
        this.messageRepository = messageRepository;
        this.annonceRepository = annonceRepository;
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

    public List<Message> getMessagesByUser(Long userId) {
        return messageRepository.findBySenderIdOrReceiverId(userId, userId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(Long senderId, Long receiverId, Long annonceId) {
        return messageRepository.findBySenderIdAndReceiverIdAndAnnonceId(senderId, receiverId, annonceId);
    }

    public Annonces getAnnonceById(Long annonceId) {
        return annonceRepository.findById(annonceId).orElse(null);
    }

    // Récupérer tous les messages liés à une annonce
    public List<Message> getMessagesByAnnonce(Long annonceId) {
        return messageRepository.findByAnnonceId(annonceId);
    }
}
