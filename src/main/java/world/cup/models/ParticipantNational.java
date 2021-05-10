package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@DiscriminatorValue("national")
public class ParticipantNational extends Participant {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    private Organisme organisme;

    @ManyToMany(fetch = FetchType.LAZY ,mappedBy="participantsN")
    @JsonIgnoreProperties(value="participantsN",allowSetters = true)
    private Set<Session> sessions =  new HashSet<>();

    public ParticipantNational() { super(); }

    @JsonIgnoreProperties({"formateurs","participantsNational","sessions"})
    public Organisme getOrganisme() {
        return organisme;
    }

    @JsonProperty
    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    @JsonIgnoreProperties(value="participantsN" ,allowSetters = true)
    public Set<Session> getSessions() {
        return sessions;
    }
    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
