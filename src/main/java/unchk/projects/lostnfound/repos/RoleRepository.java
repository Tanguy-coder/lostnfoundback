package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unchk.projects.lostnfound.models.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
    
    
}
