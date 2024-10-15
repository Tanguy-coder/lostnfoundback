package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import unchk.projects.lostnfound.models.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import unchk.projects.lostnfound.models.Message;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Tu peux ajouter des méthodes personnalisées si nécessaire

    // Par exemple, pour récupérer tous les messages reçus par un utilisateur
    List<Message> findByReceiverId(Long receiverId);

    // Pour récupérer tous les messages envoyés par un utilisateur
    List<Message> findBySenderId(Long senderId);
    
    // Trouver les messages où l'utilisateur est soit l'expéditeur (sender) soit le destinataire (receiver)
    List<Message> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}

