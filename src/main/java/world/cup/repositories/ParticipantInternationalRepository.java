package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.ParticipantInternational;

@Repository
public interface ParticipantInternationalRepository extends JpaRepository<ParticipantInternational, Long> {
}
