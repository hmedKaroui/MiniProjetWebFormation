package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.ParticipantNational;

@Repository
public interface ParticipantNationalRepository extends JpaRepository<ParticipantNational,Long> {
}
