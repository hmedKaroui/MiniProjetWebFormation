package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Participant;

@Repository
public interface ParticipantRepository <T extends Participant> extends JpaRepository<T, Long> {
    public T findByEmail(String email);
}
