package world.cup.models;

import javax.persistence.*;

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

    @Override
    public String toString() {
        return "Domaine{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
