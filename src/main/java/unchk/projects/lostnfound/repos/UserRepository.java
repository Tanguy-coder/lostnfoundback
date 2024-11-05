package unchk.projects.lostnfound.repos;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import unchk.projects.lostnfound.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmailAndPassword(String email, String password);
    Users findByUsername(String username);
    Users findUserById(long id);
}
