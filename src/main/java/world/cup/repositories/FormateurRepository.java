package world.cup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import world.cup.models.Formateur;

import java.util.Optional;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long> {
    public Optional<Formateur> findFormateurById(Long id);
}
