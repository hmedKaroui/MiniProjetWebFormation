package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.cup.exception.ResourceNotFoundException;
import world.cup.models.*;
import world.cup.repositories.*;

import java.util.List;

@Service
public class AdminService {
    private final PaysRepository paysRepository;
    private final OrganismeRepository organismeRepository;
    private final ParticipantNationalRepository participantNationalRepository;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    @Autowired
    public AdminService(PaysRepository paysRepository,OrganismeRepository organismeRepository,
                        ParticipantNationalRepository participantNationalRepository,ProfileRepository profileRepository,
                        UserRepository userRepository) {
        this.paysRepository=paysRepository;
        this.organismeRepository=organismeRepository;
        this.participantNationalRepository=participantNationalRepository;
        this.profileRepository=profileRepository;
        this.userRepository=userRepository;
    }

    //Pays Management
    public List<Pays> findAllPays(){
        return paysRepository.findAll();
    }
    public Pays findPaysById(Long id) {
        return paysRepository.findPaysById(id).orElseThrow(()->
                new ResourceNotFoundException("Pays with id: "+id+" was not found"));

    }

    public Pays addPays(Pays pays) {return paysRepository.save(pays);}
    public Pays updatePays(Long id , Pays changes) {
        Pays pays = paysRepository.findPaysById(id).orElseThrow(()->
                new ResourceNotFoundException("Pay with id: "+id+" was not found"));
        pays.setPhoto(changes.getPhoto());
        pays.setLibelle(changes.getLibelle());

        return paysRepository.save(pays);
    }
    public void deletePays(Long id) {
        Pays pays = paysRepository.findPaysById(id).orElseThrow(()->
                new ResourceNotFoundException("Pay with id "+id+" was not found"));
        paysRepository.delete(pays);
    }
    //end of Pays Managament

    //organisme Management
    public List<Organisme> findAllOrganisme(){
        return organismeRepository.findAll();
    }
    public Organisme findOrganismeById(Long id) {
        return organismeRepository.findOrganismeById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id: "+id+" was not found"));

    }

    public Organisme addOrganisme(Organisme organisme) {return organismeRepository.save(organisme);}
    public Organisme updateOrganisme(Long id , Organisme changes) {
        Organisme organisme = organismeRepository.findOrganismeById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id: "+id+" was not found"));
        organisme.setLibelle(changes.getLibelle());

        return organismeRepository.save(organisme);
    }
    public void deleteOrganisme(Long id) {
        Organisme organisme = organismeRepository.findOrganismeById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id "+id+" was not found"));
        organismeRepository.delete(organisme);
    }
    //end of organisme management

    // Profile Managament

    public List<Profile> findAllProfile(){
        return profileRepository.findAll();
    }

    //end of profile management

    //Participant National Management
    public List<ParticipantNational> findAllParticipantNational(){
        return participantNationalRepository.findAll();
    }
    public ParticipantNational findParticipantNationalById(Long id) {
        return participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id: "+id+" was not found"));

    }

    public ParticipantNational addParticipantNational(ParticipantNational participantNational) {
        Profile tmp = profileRepository.save(participantNational.getProfil());
        participantNational.setProfil(tmp);
        return participantNationalRepository.save(participantNational);
    }
    public ParticipantNational updateParticipantNational(Long id , ParticipantNational changes) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id: "+id+" was not found"));
        participantNational.setNom(changes.getNom());
        participantNational.setPrenom(changes.getPrenom());
        participantNational.setEmail(changes.getEmail());
        participantNational.setTel(changes.getTel());
        return participantNationalRepository.save(participantNational);
    }
    public ParticipantNational updateParticipantNationalProfile(Long id , ParticipantNational changes) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id: "+id+" was not found"));
        participantNational.setProfil(changes.getProfil());
        return participantNationalRepository.save(participantNational);
    }
    public ParticipantNational updateParticipantNationalOrganisme(Long id , ParticipantNational changes) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id: "+id+" was not found"));
        participantNational.setOrganisme(changes.getOrganisme());
        return participantNationalRepository.save(participantNational);
    }

    public void deleteParticipantNational(Long id) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Organisme with id "+id+" was not found"));
        participantNationalRepository.delete(participantNational);

        User user = userRepository.findUserById(id).orElseThrow(()->
                new ResourceNotFoundException("User with id "+id+" was not found"));
        userRepository.delete(user);
    }


    //end of Participant National Management
}
