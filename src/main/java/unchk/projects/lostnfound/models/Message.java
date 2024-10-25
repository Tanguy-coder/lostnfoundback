package unchk.projects.lostnfound.models;


import jakarta.persistence.*;

import java.util.Date;




@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id", nullable = false)
    private Users sender;  // Utilisateur qui envoie le message

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Users receiver;  // Utilisateur qui reçoit le message

    @Column(nullable = false)
    private String content;  // Contenu du message

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_at", nullable = false)
    private Date sentAt;  // Date et heure de l'envoi du message

    @ManyToOne
    @JoinColumn(name = "annonce_id")
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
