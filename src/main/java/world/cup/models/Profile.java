package world.cup.models;

import javax.persistence.*;

@SuppressWarnings("SpellCheckingInspection")

@Entity
@Table(name= "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROFILE_ID")
    private long id ;

    @Column(name="LIBELLE")
    private String libelle;

    public Profile() {}

    public Profile(String libelle) {
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
        return "Profile{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
