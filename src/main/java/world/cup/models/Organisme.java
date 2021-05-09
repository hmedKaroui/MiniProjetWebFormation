package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy="organisme")
    @JsonIgnoreProperties("organisme")
    private Set<Formateur> formateurs = new HashSet<>();

    @OneToMany(mappedBy="organisme")
    @JsonIgnoreProperties("organisme")
    private Set<ParticipantNational> participantsNational = new HashSet<>();

    @OneToMany(mappedBy="organisme")
    @JsonIgnoreProperties("organisme")
    private Set<Session> sessions = new HashSet<>();

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
    @JsonIgnoreProperties("organisme")
    public Set<Formateur> getFormateurs() {
        return formateurs;
    }

    public void setFormateurs(Set<Formateur> formateurs) {
        this.formateurs = formateurs;
    }
    @JsonIgnoreProperties("organisme")
    public Set<ParticipantNational> getParticipantsNational() {
        return participantsNational;
    }

    public void setParticipantsNational(Set<ParticipantNational> participantsNational) {
        this.participantsNational = participantsNational;
    }
    @JsonIgnoreProperties("organisme")
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Organisme{" +
                "id=" + id +
                ", Libelle='" + libelle + '\'' +
                '}';
    }
}
