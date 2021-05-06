package world.cup.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@DiscriminatorValue("national")
public class ParticipantNational extends Participant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn
    private Organisme organisme;

    public ParticipantNational() { super(); }

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }
}
