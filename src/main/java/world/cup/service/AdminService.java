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
    private final FormationRepository formationRepository;
    private final SessionRepository sessionRepository;
    @Autowired
    public AdminService(PaysRepository paysRepository,OrganismeRepository organismeRepository,
                        ParticipantNationalRepository participantNationalRepository,ProfileRepository profileRepository,
                        UserRepository userRepository,ParticipantInternationalRepository participantInternationalRepository,
                        FormateurRepository formateurRepository ,DomaineRepository domaineRepository,
                        FormationRepository formationRepository,SessionRepository sessionRepository) {
        this.paysRepository=paysRepository;
        this.organismeRepository=organismeRepository;
        this.participantNationalRepository=participantNationalRepository;
        this.profileRepository=profileRepository;
        this.userRepository=userRepository;
        this.participantInternationalRepository=participantInternationalRepository;
        this.formateurRepository=formateurRepository;
        this.domaineRepository = domaineRepository;
        this.formationRepository=formationRepository;
        this.sessionRepository=sessionRepository;
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
        if(organisme.getFormateurs()!=null) {
            for (Formateur f : organisme.getFormateurs()) {
                this.deleteFormateur(f.getId());

            }
        }
        if(organisme.getSessions()!=null) {
            for (Session s : organisme.getSessions()) {
                this.deleteSession(s.getId());
            }
        }
        if(organisme.getParticipantsNational()!=null) {
            for (ParticipantNational p : organisme.getParticipantsNational()) {
                this.deleteParticipantNational(p.getId());
            }
        }
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
        if(domaine.getFormations()!=null) {
            for (Formation f : domaine.getFormations()) {
                this.deleteFormation(f.getId());
            }
        }
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
        if(participantNational.getSessions()!=null) {
            for (Session s : participantNational.getSessions()) {
                deleteSessionPN(s.getId(), participantNational.getId());
            }
        }
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
        if(participantInternational.getSessions()!=null) {
            for (Session s : participantInternational.getSessions()) {
                deleteSessionPI(s.getId(), participantInternational.getId());
            }
        }
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
        if(formateur.getSessions()!=null) {
            for (Session s : formateur.getSessions()) {
                this.deleteSession(s.getId());
            }
        }
        formateurRepository.delete(formateur);
    }
    //End of formateur Management

    //Formation managament
    public List<Formation> findAllFormation(){
        return formationRepository.findAll();
    }
    public Formation findFormationById(Long id) {
        return formationRepository.findFormationById(id).orElseThrow(()->
                new ResourceNotFoundException("Formation with id: "+id+" was not found"));
    }

    public Formation addFormation(Formation formation) {
        Domaine tmp = domaineRepository.save(formation.getDomaine());
        formation.setDomaine(tmp);
        return formationRepository.save(formation);
    }
    public Formation updateFormation(Long id , Formation changes) {
        Formation formation = formationRepository.findFormationById(id).orElseThrow(()->
                new ResourceNotFoundException("Formation with id: "+id+" was not found"));
        formation.setTitre(changes.getTitre());
        formation.setTypeFormation(changes.getTypeFormation());
        formation.setNbSession(changes.getNbSession());
        formation.setDateDebut(changes.getDateDebut());
        formation.setDateFin(changes.getDateFin());
        formation.setBudget(changes.getBudget());
        formation.setDomaine(changes.getDomaine());
        return formationRepository.save(formation);
    }
    public void deleteFormation(Long id) {
        Formation formation = formationRepository.findFormationById(id).orElseThrow(()->
                new ResourceNotFoundException("Formation with id "+id+" was not found"));
        if(formation.getSessions()!=null) {
            for (Session s : formation.getSessions()) {
                deleteSessionFormations(s.getId(), formation.getId());
            }
        }
        formationRepository.delete(formation);
    }
    //end of formation management

    //Session Management
    public List<Session> findAllSession(){
        return sessionRepository.findAll();
    }
    public Session findSessionById(Long id) {
        return sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+id+" was not found"));
    }

    public Session addSession(Session session) {
        if(!organismeRepository.findOrganismeByLibelle(session.getOrganisme().getLibelle()).isPresent()) {
            Organisme tmp = organismeRepository.save(session.getOrganisme());
            session.setOrganisme(tmp);
        }
        return sessionRepository.save(session);
    }
    public Session updateSession(Long id , Session changes) {
        Session session = sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+id+" was not found"));
        session.setLieu(changes.getLieu());
        session.setDateDebut(changes.getDateDebut());
        session.setDateFin(changes.getDateFin());
        session.setNbParticipant(changes.getNbParticipant());
        session.setFormateur(changes.getFormateur());
        session.setOrganisme(changes.getOrganisme());
        return sessionRepository.save(session);
    }

    public Session updateSessionFormations(Long id , Formation newAdded) {
        Session session = sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+id+" was not found"));
        if(newAdded.getSessions().size()<newAdded.getNbSession())
            {session.getFormations().add(newAdded);}
        return sessionRepository.save(session);
    }

    public Session deleteSessionFormations(Long idS , Long idF) {
        Session session = sessionRepository.findSessionById(idS).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+idS+" was not found"));

        Formation deleatedFormation = formationRepository.findFormationById(idF).orElseThrow(()->
                new ResourceNotFoundException("Formation with id: "+idF+" was not found"));

        session.getFormations().remove(deleatedFormation);
        return sessionRepository.save(session);
    }
    public Session updateSessionPN(Long id , ParticipantNational newAdded) {
        Session session = sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+id+" was not found"));
        if(session.getParticipantsN().size() + session.getParticipantsI().size() < session.getNbParticipant())
                {session.getParticipantsN().add(newAdded);}
        return sessionRepository.save(session);
    }

    public Session deleteSessionPN(Long idS , Long idPN) {
        Session session = sessionRepository.findSessionById(idS).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+idS+" was not found"));

        ParticipantNational deleatedPN = participantNationalRepository.findParticipantNationalById(idPN).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id: "+idPN+" was not found"));

        session.getParticipantsN().remove(deleatedPN);
        return sessionRepository.save(session);
    }

    public Session updateSessionPI(Long id , ParticipantInternational newAdded) {
        Session session = sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+id+" was not found"));
        if(session.getParticipantsN().size() + session.getParticipantsI().size() < session.getNbParticipant())
        {session.getParticipantsI().add(newAdded);}
        return sessionRepository.save(session);
    }

    public Session deleteSessionPI(Long idS , Long idPI) {
        Session session = sessionRepository.findSessionById(idS).orElseThrow(()->
                new ResourceNotFoundException("Session with id: "+idS+" was not found"));

        ParticipantInternational deleatedPI = participantInternationalRepository.findParticipantInternationalById(idPI).orElseThrow(()->
                new ResourceNotFoundException("Participant National with id: "+idPI+" was not found"));

        session.getParticipantsI().remove(deleatedPI);
        return sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        Session session = sessionRepository.findSessionById(id).orElseThrow(()->
                new ResourceNotFoundException("Session with id "+id+" was not found"));
        sessionRepository.delete(session);
    }
    //end of Session Managament
}
