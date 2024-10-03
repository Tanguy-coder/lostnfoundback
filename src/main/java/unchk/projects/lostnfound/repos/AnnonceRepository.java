package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import unchk.projects.lostnfound.models.Annonces;

public interface AnnonceRepository extends JpaRepository <Annonces, Long> {
    Annonces findAnnoncesByTitre(String titre);
}
