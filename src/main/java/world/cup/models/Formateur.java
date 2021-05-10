package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import world.cup.models_enums.FormateurType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name= "formateurs")
public class Formateur  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FORMATEUR_ID")
    private long id ;

    @Column(name="NOM")
    private String nom;

    @Column(name="PRENOM")
    private String prenom;

    @Column(name="EMAIL")
    private String email;

    @Column(name="TEL")
    private int tel;

    @Column(name="TYPE_FORMATEUR")
    @Enumerated(EnumType.STRING)
    private FormateurType type;

    @ManyToOne
    @JoinColumn(name="FK_ORGANISME_ID")
    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    private Organisme organisme;

    @OneToMany(mappedBy="formateur")
    @JsonIgnoreProperties("formateur")
    private Set<Session> sessions = new HashSet<>();

    public Formateur() {}

    public Formateur(String nom, String prenom, String email, int tel, FormateurType type) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    public Organisme getOrganisme() {
        return organisme;
    }
    @JsonProperty
    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public FormateurType getType() {
        return type;
    }

    public void setType(FormateurType type) {
        this.type = type;
    }

    @JsonIgnoreProperties("formateur")
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", Nom='" + nom + '\'' +
                ", Prenom='" + prenom + '\'' +
                ", Email='" + email + '\'' +
                ", Tel=" + tel +
                ", Type=" + type +
                '}';
    }
}
