package unchk.projects.lostnfound.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unchk.projects.lostnfound.implement.AnnonceServiceImplement;
import unchk.projects.lostnfound.models.Annonces;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.AnnonceRepository;
import unchk.projects.lostnfound.repos.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/annonces"})
@EnableCaching
public class AnnonceController {
    @Autowired
    private final AnnonceRepository annonceRepository;
    private final AnnonceServiceImplement annonceService;
    private static final String UPLOAD_DIR = "uploads/";
    @Autowired
    private CacheManager cacheManager;;
    @Autowired
    private UserRepository userRepository;


    public AnnonceController(AnnonceRepository annonceRepository, AnnonceServiceImplement annonceService) {
        this.annonceRepository = annonceRepository;
        this.annonceService = annonceService;
    }

    @DeleteMapping("clear")
    public ResponseEntity<Void> clearAllCaches() {
        cacheManager
                .getCacheNames()
                .stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Annonces> getAnnonces() {
        return annonceRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> addAnnonce(
            @RequestPart("titre") String titre,
            @RequestPart("description") String description,
            @RequestPart("lieu") String lieu,
            @RequestPart("user") String user,
            @RequestPart("date") String date,
            @RequestPart("contact") String contact,
            @RequestPart(value = "image", required = false) MultipartFile file) throws IOException, ParseException {

        if (titre == null || description == null || lieu == null || contact == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some required fields are missing");
        }
        System.out.println(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date laDate = formatter.parse(date);
        Annonces a = new Annonces();

        if (!file.isEmpty()) {
            // Générer un nom unique pour le fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR, fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            String publicUrl = "/uploads/" + fileName;
            a.setUrl_image(publicUrl);
        }

        Optional<Users> userOptional = userRepository.findById(Long.parseLong(user));
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        Users u = userOptional.get();
        a.setTitre(titre);
        a.setDescription(description);
        a.setLieu(lieu);
        a.setContact(contact);
        a.setDate(laDate);
        a.setUser(u);
        a.setDate_publication(new Date());
        a.setStatus(true);
        Annonces savedAnnonce = annonceService.addAnnonce(a);
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
