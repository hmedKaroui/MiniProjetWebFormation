package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Organisme;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme,Long> {
}
