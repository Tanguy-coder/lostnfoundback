package unchk.projects.lostnfound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unchk.projects.lostnfound.implement.AnnonceServiceImplement;
import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.repos.AnnonceRepository;
import unchk.projects.lostnfound.services.AnnonceService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/annonces"})
public class AnnonceController {
    @Autowired
    private final AnnonceRepository annonceRepository;
    private final AnnonceServiceImplement annonceService;
    private static final String UPLOAD_DIR = "uploads/";

    public AnnonceController(AnnonceRepository annonceRepository, AnnonceServiceImplement annonceService) {
        this.annonceRepository = annonceRepository;
        this.annonceService = annonceService;
    }

    @GetMapping
    public List<Annonces> getAnnonces() {
        return annonceRepository.findAll();
    }

    @PostMapping( consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Annonces> addAnnonce(
            @RequestPart("annonce") Annonces annonce,
            @RequestPart("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFile.getBytes());
            annonce.setUrl_image("/" + UPLOAD_DIR + fileName);

        }

        System.out.println("paqhsdhdhedh");

        annonce.setTitre(annonce.getTitre());
        annonce.setDescription(annonce.getDescription());
        annonce.setLieu(annonce.getLieu());
        annonce.setDate(new Date());
        annonce.setStatus(true); // Par défaut, les annonces sont validées
        annonce.setUser(annonce.getUser()); // Associer l'annonce à l'utilisateur courant

        Annonces savedAnnonce = annonceService.saveAnnonce(annonce);

        return ResponseEntity.ok(savedAnnonce);
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
