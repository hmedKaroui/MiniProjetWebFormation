package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Formation;

import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    public Optional<Formation> findFormationById(Long id);
}
