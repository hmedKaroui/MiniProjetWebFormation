package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Organisme;

import java.util.Optional;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme,Long> {
    public Optional<Organisme> findOrganismeById(Long id);
}
