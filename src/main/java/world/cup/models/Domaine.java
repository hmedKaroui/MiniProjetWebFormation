package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name= "domaines")
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DOMAINE_ID")
    private long id ;

    @Column(name="LIBELLE")
    private String libelle;

    @OneToMany(mappedBy="domaine")
    @JsonIgnoreProperties("domaine")
    private Set<Formation> formations = new HashSet<>();

    public Domaine() {}

    public Domaine(String libelle) {
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @JsonIgnoreProperties("domaine")
    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }

    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
