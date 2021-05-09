package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Session;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    public Optional<Session> findSessionById(Long id);

}
