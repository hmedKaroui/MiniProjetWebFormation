package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy="pays")
    @JsonIgnoreProperties("pays")
    private Set<ParticipantInternational> participantsInternational = new HashSet<>();

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

    @JsonIgnoreProperties("pays")
    public Set<ParticipantInternational> getParticipantsInternational() {
        return participantsInternational;
    }

    public void setParticipantsInternational(Set<ParticipantInternational> participantsInternational) {
        this.participantsInternational = participantsInternational;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
