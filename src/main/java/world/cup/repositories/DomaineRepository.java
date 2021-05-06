package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Domaine;

@Repository
public interface DomaineRepository extends JpaRepository<Domaine,Long> {
}
