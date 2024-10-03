package unchk.projects.lostnfound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unchk.projects.lostnfound.implement.AnnonceServiceImplement;
import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.repos.AnnonceRepository;
import unchk.projects.lostnfound.services.AnnonceService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/annonces"})
public class AnnonceController {

    private final AnnonceRepository annonceRepository;
    private final AnnonceServiceImplement annonceService;

    public AnnonceController(AnnonceRepository annonceRepository, AnnonceServiceImplement annonceService) {
        this.annonceRepository = annonceRepository;
        this.annonceService = annonceService;
    }

    @GetMapping
    public List<Annonces> getAnnonces() {
        return annonceRepository.findAll();
    }

    @PostMapping
    public Annonces addAnnonce(@RequestBody Annonces annonce) {
        Date date = new Date();
         annonce = new Annonces();
         annonce.setTitre(annonce.getTitre());
         annonce.setDescription(annonce.getDescription());
         annonce.setLieu(annonce.getLieu());
         annonce.setDate(annonce.getDate());
         annonce.setDate_publication(date);
        return annonceService.saveAnnonce(annonce);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annonces> getAnnonceById(@PathVariable Long id) {
        Annonces annonce = annonceRepository.findById(id).orElse(null);
        if (annonce == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(annonce);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Annonces>updateAnnonce(@PathVariable Long id, @RequestBody Annonces annonceDetails) {
        Optional<Annonces> annonce = annonceRepository.findById(id);
        if (!annonce.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Annonces updateAnnonce = annonce.get();
        updateAnnonce.setTitre(annonceDetails.getTitre());
        updateAnnonce.setDescription(annonceDetails.getDescription());
        updateAnnonce.setLieu(annonceDetails.getLieu());
        updateAnnonce.setDate(annonceDetails.getDate());
        updateAnnonce.setDate_publication(annonceDetails.getDate());

        annonceRepository.save(updateAnnonce);
        return ResponseEntity.ok(updateAnnonce);
    }

}
