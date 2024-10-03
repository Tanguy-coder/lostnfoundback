package unchk.projects.lostnfound.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.repos.AnnonceRepository;
import unchk.projects.lostnfound.services.AnnonceService;

import java.util.List;

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
}
