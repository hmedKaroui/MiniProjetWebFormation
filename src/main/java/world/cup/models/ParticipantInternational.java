package world.cup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue("international")
public class ParticipantInternational extends Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("participantsInternational")
    private Pays pays ;

    public ParticipantInternational() { super(); }

    @JsonIgnoreProperties("participantsInternational")
    public Pays getPays() {
        return pays;
    }
    @JsonProperty
    public void setPays(Pays pays) {
        this.pays = pays;
    }
}
