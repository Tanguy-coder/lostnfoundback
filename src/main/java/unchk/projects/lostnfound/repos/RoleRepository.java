package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import unchk.projects.lostnfound.models.Roles;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
