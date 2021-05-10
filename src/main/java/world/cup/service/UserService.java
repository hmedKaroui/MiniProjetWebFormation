package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import world.cup.exception.ResourceNotFoundException;
import world.cup.models.*;
import world.cup.repositories.*;

import java.util.List;

@Service
public class UserService {
    private final FormationRepository formationRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final ParticipantNationalRepository participantNationalRepository;
    private final ParticipantInternationalRepository participantInternationalRepository;
    private final SessionRepository sessionRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    public UserService(FormationRepository formationRepository,ParticipantRepository participantRepository,
                       UserRepository userRepository,ParticipantNationalRepository participantNationalRepository,
                       ParticipantInternationalRepository participantInternationalRepository,SessionRepository sessionRepository) {
        this.formationRepository=formationRepository;
        this.participantRepository=participantRepository;
        this.userRepository=userRepository;
        this.participantNationalRepository=participantNationalRepository;
        this.participantInternationalRepository=participantInternationalRepository;
        this.sessionRepository=sessionRepository;
    }

    //Formation
    public List<Formation> findAllFormation(){
        return formationRepository.findAll();
    }
    public Formation findFormationById(Long id) {
        return formationRepository.findFormationById(id).orElseThrow(()->
                new ResourceNotFoundException("Formation with id: "+id+" was not found"));
    }

    //Participant
    public Participant findParticipantById(Long id) {
        Participant p = participantRepository.findParticipantById(id).orElseThrow(()->
                new ResourceNotFoundException("Formation with id: "+id+" was not found"));
        if (p instanceof ParticipantNational) {
            return (ParticipantNational)p;
        }
        else if( p instanceof ParticipantInternational) {
            return (ParticipantInternational)p;
        }
        else {
            return null;
        }
    }

    public Participant updateParticipantSessions(Long id , Session newAdded) {
        Participant p = participantRepository.findParticipantById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant with id: "+id+" was not found"));
        Session s = sessionRepository.findSessionById(newAdded.getId()).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+newAdded.getId()+" was not found"));
        if (p instanceof ParticipantNational) {
            ParticipantNational pN = (ParticipantNational)p;
            if(s.getParticipantsN().size() + s.getParticipantsI().size() < s.getNbParticipant()) {
                s.getParticipantsN().add(pN);
                sessionRepository.save(s);
            }
            return pN;
        }
        else if ( p instanceof ParticipantInternational) {
            ParticipantInternational pI = (ParticipantInternational) p;
            if (s.getParticipantsN().size() + s.getParticipantsI().size() < s.getNbParticipant()) {
                s.getParticipantsI().add(pI);
                sessionRepository.save(s);

            }
            return pI;
        }
        else return null;
    }

    public Participant deleteParticipantSessions(Long idP , Long idS) {
        Participant p = participantRepository.findParticipantById(idP).orElseThrow(()->
                new ResourceNotFoundException("Participant with id: "+idP+" was not found"));
        Session s = sessionRepository.findSessionById(idS).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+idP+" was not found"));
        if (p instanceof ParticipantNational) {
            ParticipantNational pN = (ParticipantNational)p;
            s.getParticipantsN().remove(pN);
            sessionRepository.save(s);
            return pN;
        }
        else if ( p instanceof ParticipantInternational) {
            ParticipantInternational pI = (ParticipantInternational) p;
            s.getParticipantsI().remove(pI);
            sessionRepository.save(s);
            return  pI;
            }
        else return null;
        }

    //User
    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(()->
                new ResourceNotFoundException("User with id: "+id+" was not found"));
    }
    public User updateUser(Long id , User changes) {
        User user = userRepository.findUserById(id).orElseThrow(()->
                new ResourceNotFoundException("User with id: "+id+" was not found"));
        user.setUsername(changes.getUsername());
        user.setEmail(changes.getEmail());
        user.setPassword(encoder.encode(changes.getPassword()));
        return userRepository.save(user);
    }
}
