package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Organisme;
import world.cup.models.ParticipantNational;
import world.cup.models.Pays;
import world.cup.models.Profile;
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

    //Profile Management

    //End of Profile Management
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfile() {
        List<Profile> Profiles = adminService.findAllProfile();
        return new ResponseEntity<>(Profiles, HttpStatus.OK);
    }
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
}
