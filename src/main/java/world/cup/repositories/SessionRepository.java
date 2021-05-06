package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

}
