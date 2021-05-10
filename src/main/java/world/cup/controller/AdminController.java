package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.cup.models.*;
import world.cup.service.AdminService;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService=adminService;
    }

    //Pays Managament
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pays")
    public ResponseEntity<List<Pays>> getAllPays() {
        List<Pays> pays = adminService.findAllPays();
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pays/{id}")
    public ResponseEntity<Pays> getPaysById(@PathVariable Long id) {
        Pays pays = adminService.findPaysById(id);
        return new ResponseEntity<>(pays,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/pays/add")
    public ResponseEntity<Pays> addPays(@RequestBody Pays pays) {
        Pays p = adminService.addPays(pays);
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/pays/update/{id}")
    public ResponseEntity<Pays> updatePays(@PathVariable Long id ,@RequestBody Pays changes) {
        Pays pays = adminService.updatePays(id,changes);
        return new ResponseEntity<>(pays,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/pays/delete/{id}")
    public ResponseEntity<?> deletePays(@PathVariable Long id) {
        adminService.deletePays(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //End Of Pays Management

    //Organism Management
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/organismes")
    public ResponseEntity<List<Organisme>> getAllOrganisme() {
        List<Organisme> organismes = adminService.findAllOrganisme();
        return new ResponseEntity<>(organismes, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/organismes/{id}")
    public ResponseEntity<Organisme> getOrganismeById(@PathVariable Long id) {
        Organisme organisme = adminService.findOrganismeById(id);
        return new ResponseEntity<>(organisme,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/organismes/add")
    public ResponseEntity<Organisme> addOrganisme(@RequestBody Organisme organisme) {
        Organisme o = adminService.addOrganisme(organisme);
        return new ResponseEntity<>(o,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/organismes/update/{id}")
    public ResponseEntity<Organisme> updateOrganisme(@PathVariable Long id ,@RequestBody Organisme changes) {
        Organisme organisme = adminService.updateOrganisme(id,changes);
        return new ResponseEntity<>(organisme,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/organismes/delete/{id}")
    public ResponseEntity<?> deleteOrganisme(@PathVariable Long id) {
        adminService.deleteOrganisme(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //End Of Organism Managament

    // Domaine endpoints
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/domaines")
    public ResponseEntity<List<Domaine>> getAllDomaine() {
        List<Domaine> domaines = adminService.findAllDomaine();
        return new ResponseEntity<>(domaines, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/domaines/{id}")
    public ResponseEntity<Domaine> getDomaineById(@PathVariable Long id) {
        Domaine domaine = adminService.findDomaineById(id);
        return new ResponseEntity<>(domaine,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/domaines/add")
    public ResponseEntity<Domaine> addDomaine(@RequestBody Domaine domaine) {
        Domaine d = adminService.addDomaine(domaine);
        return new ResponseEntity<>(d,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/domaines/update/{id}")
    public ResponseEntity<Domaine> updateDomaine(@PathVariable Long id ,@RequestBody Domaine changes) {
        Domaine domaine = adminService.updateDomaine(id,changes);
        return new ResponseEntity<>(domaine,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/domaines/delete/{id}")
    public ResponseEntity<?> deleteDomaine(@PathVariable Long id) {
        adminService.deleteDomaine(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // End Domaine endpoints

    //Profile Management
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfile() {
        List<Profile> Profiles = adminService.findAllProfile();
        return new ResponseEntity<>(Profiles, HttpStatus.OK);
    }
    //End of Profile Management


    //Participant National Managament
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/participantsNational")
    public ResponseEntity<List<ParticipantNational>> getAllParticipantNational() {
        List<ParticipantNational> participantsNational = adminService.findAllParticipantNational();
        return new ResponseEntity<>(participantsNational, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/participantsNational/{id}")
    public ResponseEntity<ParticipantNational> getParticipantNationalById(@PathVariable Long id) {
        ParticipantNational participantNational = adminService.findParticipantNationalById(id);
        return new ResponseEntity<>(participantNational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/participantsNational/add")
    public ResponseEntity<ParticipantNational> addParticipantNational(@RequestBody ParticipantNational participantNational) {
        ParticipantNational p = adminService.addParticipantNational(participantNational);
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsNational/update/{id}")
    public ResponseEntity<ParticipantNational> updateParticipantNational(@PathVariable Long id ,@RequestBody ParticipantNational changes) {
        ParticipantNational participantNational = adminService.updateParticipantNational(id,changes);
        return new ResponseEntity<>(participantNational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsNational/updateProfile/{id}")
    public ResponseEntity<ParticipantNational> updateParticipantNationalProfile(@PathVariable Long id ,@RequestBody ParticipantNational changes) {
        ParticipantNational participantNational = adminService.updateParticipantNationalProfile(id,changes);
        return new ResponseEntity<>(participantNational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsNational/updateOrganisme/{id}")
    public ResponseEntity<ParticipantNational> updateParticipantNationalOrganisme(@PathVariable Long id ,@RequestBody ParticipantNational changes) {
        ParticipantNational participantNational = adminService.updateParticipantNationalOrganisme(id,changes);
        return new ResponseEntity<>(participantNational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/participantsNational/delete/{id}")
    public ResponseEntity<?> deleteParticipantNational(@PathVariable Long id) {
        adminService.deleteParticipantNational(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //End of Participant National Managament

    //Participant International endpoints
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/participantsInternational")
    public ResponseEntity<List<ParticipantInternational>> getAllParticipantInternational() {
        List<ParticipantInternational> participantInternational = adminService.findAllParticipantInternational();
        return new ResponseEntity<>(participantInternational, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/participantsInternational/{id}")
    public ResponseEntity<ParticipantInternational> getParticipantInternationalById(@PathVariable Long id) {
        ParticipantInternational participantInternational = adminService.findParticipantInternationalById(id);
        return new ResponseEntity<>(participantInternational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/participantsInternational/add")
    public ResponseEntity<ParticipantInternational> addParticipantInternational(@RequestBody ParticipantInternational participantInternational) {
        ParticipantInternational p = adminService.addParticipantInternational(participantInternational);
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsInternational/update/{id}")
    public ResponseEntity<ParticipantInternational> updateParticipantInternational(@PathVariable Long id ,@RequestBody ParticipantInternational changes) {
        ParticipantInternational participantInternational = adminService.updateParticipantInternational(id,changes);
        return new ResponseEntity<>(participantInternational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsInternational/updateProfile/{id}")
    public ResponseEntity<ParticipantInternational> updateParticipantInternationalProfile(@PathVariable Long id ,@RequestBody ParticipantInternational changes) {
        ParticipantInternational participantInternational = adminService.updateParticipantInternationalProfile(id,changes);
        return new ResponseEntity<>(participantInternational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/participantsInternational/updatePays/{id}")
    public ResponseEntity<ParticipantInternational> updateParticipantInternationalPays(@PathVariable Long id ,@RequestBody ParticipantInternational changes) {
        ParticipantInternational participantInternational = adminService.updateParticipantInternationalPays(id,changes);
        return new ResponseEntity<>(participantInternational,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/participantsInternational/delete/{id}")
    public ResponseEntity<?> deleteParticipantInternational(@PathVariable Long id) {
        adminService.deleteParticipantInternational(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //End of Participant Internation endpoints

    //Formateur endpoints
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/formateurs")
    public ResponseEntity<List<Formateur>> getAllFormateur() {
        List<Formateur> formateurs = adminService.findAllFormateur();
        return new ResponseEntity<>(formateurs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/formateurs/{id}")
    public ResponseEntity<Formateur> getFormateurById(@PathVariable Long id) {
        Formateur formateur = adminService.findFormateurById(id);
        return new ResponseEntity<>(formateur,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/formateurs/add")
    public ResponseEntity<Formateur> addFormateur(@RequestBody Formateur formateur) {
        Formateur f = adminService.addFormateur(formateur);
        return new ResponseEntity<>(f,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/formateurs/update/{id}")
    public ResponseEntity<Formateur> updateFormateur(@PathVariable Long id ,@RequestBody Formateur changes) {
        Formateur formateur = adminService.updateFormateur(id,changes);
        return new ResponseEntity<>(formateur,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/formateurs/updateOrganisme/{id}")
    public ResponseEntity<Formateur> updateFormateurOrganisme(@PathVariable Long id ,@RequestBody Formateur changes) {
        Formateur formateur = adminService.updateFormateurOrganisme(id,changes);
        return new ResponseEntity<>(formateur,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/formateurs/delete/{id}")
    public ResponseEntity<?> deleteFormateur(@PathVariable Long id) {
        adminService.deleteFormateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //End of Formateur endpoints

    //Formation endpoints
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/formations")
    public ResponseEntity<List<Formation>> getAllFormation() {
        List<Formation> formations = adminService.findAllFormation();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/formations/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Formation formation = adminService.findFormationById(id);
        return new ResponseEntity<>(formation,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/formations/add")
    public ResponseEntity<Formation> addFormation(@RequestBody Formation formation) {
        Formation f = adminService.addFormation(formation);
        return new ResponseEntity<>(f,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/formations/update/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id ,@RequestBody Formation changes) {
        Formation formation = adminService.updateFormation(id,changes);
        return new ResponseEntity<>(formation,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/formations/delete/{id}")
    public ResponseEntity<?> deleteFormation(@PathVariable Long id) {
        adminService.deleteFormation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //end of Formation endpoints

    //Session endpoints
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getAllSession() {
        List<Session> sessions = adminService.findAllSession();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/sessions/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = adminService.findSessionById(id);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/sessions/add")
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        Session s = adminService.addSession(session);
        return new ResponseEntity<>(s,HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/sessions/update/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id ,@RequestBody Session changes) {
        Session session = adminService.updateSession(id,changes);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/sessions/delete/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        adminService.deleteSession(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/sessions/updateFormations/{id}")
    public ResponseEntity<Session> updateSessionFormations(@PathVariable Long id ,@RequestBody Formation changes) {
        Session session = adminService.updateSessionFormations(id,changes);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/sessions/{idSesssion}/deleteFormations/{idFormation}")
    public ResponseEntity<Session> deleteSessionFormations(@PathVariable("idSesssion") Long idS ,@PathVariable("idFormation") Long idF) {
        Session session = adminService.deleteSessionFormations(idS,idF);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/sessions/updatePN/{id}")
    public ResponseEntity<Session> updateSessionPN(@PathVariable Long id ,@RequestBody ParticipantNational changes) {
        Session session = adminService.updateSessionPN(id,changes);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/sessions/{idSesssion}/deletePN/{idPN}")
    public ResponseEntity<Session> deleteSessionPN(@PathVariable("idSesssion") Long idS ,@PathVariable("idPN") Long idPN) {
        Session session = adminService.deleteSessionPN(idS,idPN);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/sessions/updatePI/{id}")
    public ResponseEntity<Session> updateSessionPI(@PathVariable Long id ,@RequestBody ParticipantInternational changes) {
        Session session = adminService.updateSessionPI(id,changes);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/sessions/{idSesssion}/deletePI/{idPI}")
    public ResponseEntity<Session> deleteSessionPI(@PathVariable("idSesssion") Long idS ,@PathVariable("idPI") Long idPI) {
        Session session = adminService.deleteSessionPI(idS,idPI);
        return new ResponseEntity<>(session,HttpStatus.OK);
    }
    //end of Session end points
}
