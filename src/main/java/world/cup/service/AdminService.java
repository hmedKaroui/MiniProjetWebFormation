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
    private final ParticipantInternationalRepository participantInternationalRepository;
    private final FormateurRepository formateurRepository;
    private final DomaineRepository domaineRepository;
    @Autowired
    public AdminService(PaysRepository paysRepository,OrganismeRepository organismeRepository,
                        ParticipantNationalRepository participantNationalRepository,ProfileRepository profileRepository,
                        UserRepository userRepository,ParticipantInternationalRepository participantInternationalRepository,
                        FormateurRepository formateurRepository ,DomaineRepository domaineRepository ) {
        this.paysRepository=paysRepository;
        this.organismeRepository=organismeRepository;
        this.participantNationalRepository=participantNationalRepository;
        this.profileRepository=profileRepository;
        this.userRepository=userRepository;
        this.participantInternationalRepository=participantInternationalRepository;
        this.formateurRepository=formateurRepository;
        this.domaineRepository = domaineRepository;
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
        if(pays.getParticipantsInternational()!=null) {
            for (ParticipantInternational p : pays.getParticipantsInternational()) {
                this.deleteParticipantInternational(p.getId());
            }
        }
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

    //Domaine Management
    public List<Domaine> findAllDomaine(){
        return domaineRepository.findAll();
    }
    public Domaine findDomaineById(Long id) {
        return domaineRepository.findDomaineById(id).orElseThrow(()->
                new ResourceNotFoundException("Domaine with id: "+id+" was not found"));

    }

    public Domaine addDomaine(Domaine domaine) {return domaineRepository.save(domaine);}
    public Domaine updateDomaine(Long id , Domaine changes) {
        Domaine domaine = domaineRepository.findDomaineById(id).orElseThrow(()->
                new ResourceNotFoundException("Domaine with id: "+id+" was not found"));
        domaine.setLibelle(changes.getLibelle());

        return domaineRepository.save(domaine);
    }
    public void deleteDomaine(Long id) {
        Domaine domaine = domaineRepository.findDomaineById(id).orElseThrow(()->
                new ResourceNotFoundException("Domaine with id "+id+" was not found"));
        domaineRepository.delete(domaine);
    }
    //End of Domaine Managament

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
                new ResourceNotFoundException("Participant National with id: "+id+" was not found"));
        participantNational.setNom(changes.getNom());
        participantNational.setPrenom(changes.getPrenom());
        participantNational.setEmail(changes.getEmail());
        participantNational.setTel(changes.getTel());
        return participantNationalRepository.save(participantNational);
    }
    public ParticipantNational updateParticipantNationalProfile(Long id , ParticipantNational changes) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id: "+id+" was not found"));
        participantNational.setProfil(changes.getProfil());
        return participantNationalRepository.save(participantNational);
    }
    public ParticipantNational updateParticipantNationalOrganisme(Long id , ParticipantNational changes) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id: "+id+" was not found"));
        participantNational.setOrganisme(changes.getOrganisme());
        return participantNationalRepository.save(participantNational);
    }

    public void deleteParticipantNational(Long id) {
        ParticipantNational participantNational = participantNationalRepository.findParticipantNationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id "+id+" was not found"));
        participantNationalRepository.delete(participantNational);

        User user = userRepository.findUserById(id).orElseThrow(()->
                new ResourceNotFoundException("User with id "+id+" was not found"));
        userRepository.delete(user);
    }
    //end of Participant National Management

    //Participant International Management
    public List<ParticipantInternational> findAllParticipantInternational(){
        return participantInternationalRepository.findAll();
    }
    public ParticipantInternational findParticipantInternationalById(Long id) {
        return participantInternationalRepository.findParticipantInternationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant International with id: "+id+" was not found"));

    }

    public ParticipantInternational addParticipantInternational(ParticipantInternational participantInternational) {
        Profile tmp = profileRepository.save(participantInternational.getProfil());
        participantInternational.setProfil(tmp);
        return participantInternationalRepository.save(participantInternational);
    }
    public ParticipantInternational updateParticipantInternational(Long id , ParticipantInternational changes) {
        ParticipantInternational participantInternational = participantInternationalRepository.findParticipantInternationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant International with id: "+id+" was not found"));
        participantInternational.setNom(changes.getNom());
        participantInternational.setPrenom(changes.getPrenom());
        participantInternational.setEmail(changes.getEmail());
        participantInternational.setTel(changes.getTel());
        return participantInternationalRepository.save(participantInternational);
    }
    public ParticipantInternational updateParticipantInternationalProfile(Long id , ParticipantInternational changes) {
        ParticipantInternational participantInternational = participantInternationalRepository.findParticipantInternationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant International with id: "+id+" was not found"));
        participantInternational.setProfil(changes.getProfil());
        return participantInternationalRepository.save(participantInternational);
    }
    public ParticipantInternational updateParticipantInternationalPays(Long id , ParticipantInternational changes) {
        ParticipantInternational participantInternational = participantInternationalRepository.findParticipantInternationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant International with id: "+id+" was not found"));
        participantInternational.setPays(changes.getPays());
        return participantInternationalRepository.save(participantInternational);
    }

    public void deleteParticipantInternational(Long id) {
        ParticipantInternational participantInternational = participantInternationalRepository.findParticipantInternationalById(id).orElseThrow(()->
                new ResourceNotFoundException("Participant International with id "+id+" was not found"));
        participantInternationalRepository.delete(participantInternational);

        User user = userRepository.findUserById(id).orElseThrow(()->
                new ResourceNotFoundException("User with id "+id+" was not found"));
        userRepository.delete(user);
    }
    //End Of Participant International Managament

    //Formateur Management
    public List<Formateur> findAllFormateur(){
        return formateurRepository.findAll();
    }
    public Formateur findFormateurById(Long id) {
        return formateurRepository.findFormateurById(id).orElseThrow(()->
                new ResourceNotFoundException("Formateur with id: "+id+" was not found"));

    }

    public Formateur addFormateur(Formateur formateur) {return formateurRepository.save(formateur);}
    public Formateur updateFormateur(Long id , Formateur changes) {
        Formateur formateur = formateurRepository.findFormateurById(id).orElseThrow(()->
                new ResourceNotFoundException("Formateur with id: "+id+" was not found"));
        formateur.setNom(changes.getNom());
        formateur.setPrenom(changes.getPrenom());
        formateur.setEmail(changes.getEmail());
        formateur.setTel(changes.getTel());
        formateur.setType(changes.getType());

        return formateurRepository.save(formateur);
    }
    public Formateur updateFormateurOrganisme(Long id , Formateur changes) {
        Formateur formateur = formateurRepository.findFormateurById(id).orElseThrow(()->
                new ResourceNotFoundException("Formateur National with id: "+id+" was not found"));
        formateur.setOrganisme(changes.getOrganisme());
        return formateurRepository.save(formateur);
    }
    public void deleteFormateur(Long id) {
        Formateur formateur = formateurRepository.findFormateurById(id).orElseThrow(()->
                new ResourceNotFoundException("Formateur with id "+id+" was not found"));
        formateurRepository.delete(formateur);
    }
    //End of formateur Management
}
