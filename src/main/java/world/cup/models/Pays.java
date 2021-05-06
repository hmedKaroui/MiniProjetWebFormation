package world.cup.models;

import javax.persistence.*;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name= "pays")
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PAYS_ID")
    private long id;

    @Column(name="PHOTO")
    private String photo;

    @Column(name="LIBELLE")
    private String libelle;

    public Pays() {}

    public Pays(String photo, String libelle) {
        this.photo = photo;
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
