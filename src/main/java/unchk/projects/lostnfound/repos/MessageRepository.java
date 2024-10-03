package unchk.projects.lostnfound.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import unchk.projects.lostnfound.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
