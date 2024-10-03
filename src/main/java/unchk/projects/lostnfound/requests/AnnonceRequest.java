package unchk.projects.lostnfound.requests;

import java.util.Date;

public class AnnonceRequest {
    private String titre;
    private String description;
    private String url_image;
    private String repertoire;
    private String contact;
    private Date date_publication;
    private String localisation;
    private String lieu;
    private Long user_id;

    public AnnonceRequest() {
        super();
    }

    public AnnonceRequest(String description, String url_image, String repertoire, String contact, Date date_publication, String localisation, String lieu, Long user_id) {
        this.description = description;
        this.url_image = url_image;
        this.repertoire = repertoire;
        this.contact = contact;
        this.date_publication = date_publication;
        this.localisation = localisation;
        this.lieu = lieu;
        this.user_id = user_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
