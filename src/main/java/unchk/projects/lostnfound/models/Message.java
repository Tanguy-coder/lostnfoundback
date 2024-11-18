package unchk.projects.lostnfound.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import java.util.Date;

import org.springframework.ws.transport.http.AbstractHttpSenderConnection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "messages")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id", nullable = false)
   
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Users sender;// Utilisateur qui envoie le message
    
    
    
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Users receiver;// Utilisateur qui reçoit le message

    
    
    
    @Column(nullable = false)
    private String content;  // Contenu du message

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_at", nullable = false)
    private Date sentAt;  // Date et heure de l'envoi du message

    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "annonce_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Annonces annonce;

    // Constructeur sans argument
    public Message() {
        super();
    }

    public Message(Users sender, Users receiver, String content, Date sentAt, Annonces annonce) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sentAt = sentAt;
        this.annonce = annonce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public Annonces getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonces annonce) {
        this.annonce = annonce;
    }

}
