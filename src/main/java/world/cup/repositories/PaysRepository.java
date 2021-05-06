package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Pays;

import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays,Long> {
    public Optional<Pays> findPaysById(Long id);
}
