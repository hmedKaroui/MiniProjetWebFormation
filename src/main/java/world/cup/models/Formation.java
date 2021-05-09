package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import world.cup.models_enums.FormationType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "formations")
public class Formation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="FORMATION_ID")
        private long id;

        @Column(name="TITRE")
        private String titre ;

        @Column(name="TYPE_FORMATION")
        @Enumerated(EnumType.STRING)
        private FormationType typeFormation;

        @Column(name="NB_SESSION")
        private int nbSession;

        @Column(name="DATE_DEBUT")
        private Date dateDebut;

        @Column(name="DATE_FIN")
        private Date dateFin;

        @Column(name="BUDGET")
        private double budget;

        @ManyToOne
        @JoinColumn(name="FK_DOMAINE_ID")
        @JsonIgnoreProperties("formations")
        private Domaine domaine;

        @ManyToMany(fetch = FetchType.LAZY, mappedBy="formations")
        @JsonIgnoreProperties("formations")
        private Set<Session> sessions =  new HashSet<>();

    public Formation() {}

    public Formation(String titre, FormationType typeFormation, int nbSession, Date dateDebut, Date dateFin, double budget) {
        this.titre = titre;
        this.typeFormation = typeFormation;
        this.nbSession = nbSession;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.budget = budget;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public FormationType getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(FormationType typeFormation) {
        this.typeFormation = typeFormation;
    }

    public int getNbSession() {
        return nbSession;
    }

    public void setNbSession(int nbSession) {
        this.nbSession = nbSession;
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

    @JsonIgnoreProperties("formations")
    public Domaine getDomaine() {
        return domaine;
    }
    @JsonProperty
    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }


    @JsonIgnoreProperties("formations")
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id=" + id +
                ", Titre='" + titre + '\'' +
                ", typeFormation=" + typeFormation +
                ", nbSession=" + nbSession +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", Budget=" + budget +
                '}';
    }
}
