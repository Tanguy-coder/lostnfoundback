package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unchk.projects.lostnfound.models.Annonces;

import java.util.List;

public interface AnnonceRepository extends JpaRepository <Annonces, Long> {
    Annonces findAnnoncesByTitre(String titre);
    @Query("SELECT a FROM Annonces a ORDER BY a.date_publication DESC")
    List<Annonces> getLatestAnnnonces();
}
