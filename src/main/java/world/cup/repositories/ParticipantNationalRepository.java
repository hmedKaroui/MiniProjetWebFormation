package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.ParticipantNational;

import java.util.Optional;

@Repository
public interface ParticipantNationalRepository extends JpaRepository<ParticipantNational,Long> {
    public Optional<ParticipantNational> findParticipantNationalById(Long id);


}
