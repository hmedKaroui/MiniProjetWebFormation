package world.cup.models;

import javax.persistence.*;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name= "organismes")
public class Organisme {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ORGANISME_ID")
    private long id ;

    @Column(name="LIBELLE")
    private String libelle ;

    public Organisme() {}

    public Organisme(String libelle) {
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
        return "Organisme{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
