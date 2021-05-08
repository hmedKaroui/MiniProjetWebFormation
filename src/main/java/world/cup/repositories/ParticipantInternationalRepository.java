package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.ParticipantInternational;

import java.util.Optional;

@Repository
public interface ParticipantInternationalRepository extends JpaRepository<ParticipantInternational, Long> {
    public Optional<ParticipantInternational> findParticipantInternationalById(Long id);

}
