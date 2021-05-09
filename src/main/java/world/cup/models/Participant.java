package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name= "participants")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="participant_type")
public abstract class Participant {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PARTICIPANT_ID")
    private long id ;

    @Column(name="NOM")
    private String nom;

    @Column(name="PRENOM")
    private String prenom;

    @Column(name="EMAIL")
    private String email;

    @Column(name="TEL")
    private int tel;

    @ManyToOne
    @JoinColumn(name="FK_PROFIL_ID")
    private Profile profil;

    public Participant() {
    }

    public Participant(String nom, String prenom, String email, int tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Profile getProfil() {
        return profil;
    }

    public void setProfil(Profile profil) {
        this.profil = profil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", Nom='" + nom + '\'' +
                ", Prenom='" + prenom + '\'' +
                ", Email='" + email + '\'' +
                ", Tel=" + tel +
                ", Profil=" + profil +
                '}';
    }
}
