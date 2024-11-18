package unchk.projects.lostnfound.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.repos.AnnonceRepository;
import unchk.projects.lostnfound.services.AnnonceService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnnonceServiceImplement implements AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;


    @Override
    public Annonces findAnnonceByTitre(String titre) {
        return annonceRepository.findAnnoncesByTitre(titre);
    }

    @Override
    public Annonces saveAnnonce(Annonces annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public Annonces addAnnonce(Annonces annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public Annonces updateAnnonce(Annonces annonce) {
        return annonceRepository.save(annonce);
    }

    @Override
    public void deleteAnnonce(Annonces annonce) {
        annonceRepository.delete(annonce);
    }

    @Override
    public void deleteAnnonceById(Long id) {
        annonceRepository.deleteById(id);
    }

    @Override
    public Annonces findAnnonceById(Long id) {
        return annonceRepository.findById(id).get();
    }

    @Override
    public List<Annonces> findAllAnnonces() {
        return annonceRepository.findAll();
    }
    
    public void deleteAnnonce(Long annonceId) {
        if (annonceRepository.existsById(annonceId)) {
            annonceRepository.deleteById(annonceId);
        } else {
            throw new IllegalArgumentException("Annonce non trouvée.");
        }
    }
   
    
  
    
    
}
    

