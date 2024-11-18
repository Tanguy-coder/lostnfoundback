package unchk.projects.lostnfound.services;

import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Annonces;

import java.util.List;

@Service
public interface AnnonceService {
    Annonces findAnnonceByTitre(String titre);
    Annonces saveAnnonce(Annonces annonce);
    Annonces addAnnonce(Annonces annonce);
    Annonces updateAnnonce(Annonces annonce);
    void deleteAnnonce(Annonces annonce);
    void deleteAnnonceById(Long id);
    Annonces findAnnonceById(Long id);
    List<Annonces> findAllAnnonces();
    void deleteAnnonce(Long annonceId);
    
    
}
