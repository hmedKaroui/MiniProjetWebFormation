package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("international")
public class ParticipantInternational extends Participant {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("participantsInternational")
    private Pays pays ;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="participantsI")
    @JsonIgnoreProperties(value="participantsI" ,allowSetters = true)
    private Set<Session> sessions =  new HashSet<>();

    public ParticipantInternational() { super(); }


    @JsonIgnoreProperties("participantsInternational")
    public Pays getPays() {
        return pays;
    }
    @JsonProperty
    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @JsonIgnoreProperties(value="participantsI" ,allowSetters = true)
    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
