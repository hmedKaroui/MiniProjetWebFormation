package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "sessions")
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SESSION_ID")
    private long id;

    @Column(name="LIEU")
    private String lieu ;

    @Column(name="DATE_DEBUT")
    private Date dateDebut;

    @Column(name="DATE_FIN")
    private Date dateFin;

    @Column(name="NB_PARTICIPANT")
    private int nbParticipant;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("sessions")
    private Formateur formateur;

    @ManyToOne
    @JoinColumn(name="FK_ORGANISME_ID")
    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    private Organisme organisme;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_SESSION_FORMATIONS",
            joinColumns={@JoinColumn(name="SESSION_ID")},
            inverseJoinColumns={@JoinColumn(name ="FORMATION_ID")})
    @JsonIgnoreProperties("sessions")
    private Set<Formation> formations= new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_SESSION_PARTICIPANTNATIONAL",
            joinColumns={@JoinColumn(name="SESSION_ID")},
            inverseJoinColumns={@JoinColumn(name ="PARTICIPANT_ID")})
    @JsonIgnoreProperties("sessions")
    private Set<ParticipantNational> participantsN = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_SESSION_PARTICIPANTINTERNATIONAL",
            joinColumns={@JoinColumn(name="SESSION_ID")},
            inverseJoinColumns={@JoinColumn(name ="PARTICIPANT_ID")})
    @JsonIgnoreProperties("sessions")
    private Set<ParticipantInternational> participantsI = new HashSet<>();

    public Session() {}

    public Session(String lieu, Date dateDebut, Date dateFin, int nbParticipant) {
        this.lieu = lieu;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbParticipant = nbParticipant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbParticipant() {
        return nbParticipant;
    }

    public void setNbParticipant(int nbParticipant) {
        this.nbParticipant = nbParticipant;
    }

    @JsonIgnoreProperties("sessions")
    public Formateur getFormateur() {
        return formateur;
    }
    @JsonProperty
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    public Organisme getOrganisme() {
        return organisme;
    }
    @JsonProperty
    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    @JsonIgnoreProperties("sessions")
    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }

    @JsonIgnoreProperties("sessions")
    public Set<ParticipantNational> getParticipantsN() {
        return participantsN;
    }

    public void setParticipantsN(Set<ParticipantNational> participantsN) {
        this.participantsN = participantsN;
    }
    @JsonIgnoreProperties("sessions")
    public Set<ParticipantInternational> getParticipantsI() {
        return participantsI;
    }

    public void setParticipantsI(Set<ParticipantInternational> participantsI) {
        this.participantsI = participantsI;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", Lieu='" + lieu + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", nbParticipant=" + nbParticipant +
                '}';
    }
}
