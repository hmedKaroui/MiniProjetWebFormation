package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Domaine;

import java.util.Optional;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine,Long> {
    public Optional<Domaine> findDomaineById(Long id);
}
