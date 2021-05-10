package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.cup.models.*;
import world.cup.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/participant")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/formations")
    public ResponseEntity<List<Formation>> getAllFormation() {
        List<Formation> formations = userService.findAllFormation();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/formations/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable Long id) {
        Formation formation = userService.findFormationById(id);
        return new ResponseEntity<>(formation,HttpStatus.OK);
    }

    //
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/userDetails/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Participant p  = userService.findParticipantById(id);
        if (p instanceof ParticipantNational) {
            return new ResponseEntity<>((ParticipantNational)p,HttpStatus.OK);
        }
        else if( p instanceof ParticipantInternational) {
            return new ResponseEntity<>((ParticipantInternational) p, HttpStatus.OK);
        }
        else {
            return null;
        }
    }
    //
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id ,@RequestBody User changes) {
        User user = userService.updateUser(id,changes);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    //

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/userDetails/updateSession/{id}")
    public ResponseEntity<Participant> updateParticipantSessions(@PathVariable Long id ,@RequestBody Session changes) {
        Participant p = userService.updateParticipantSessions(id,changes);
        if (p instanceof ParticipantNational) {
            return new ResponseEntity<>((ParticipantNational)p,HttpStatus.OK);
        }
        else if( p instanceof ParticipantInternational) {
            return new ResponseEntity<>((ParticipantInternational) p, HttpStatus.OK);
        }
        else {
            return null;
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/userDetails/{idParticipant}/deleteSession/{idSession}")
    public ResponseEntity<Participant> deleteParticipantSessions(@PathVariable("idParticipant") Long idP ,@PathVariable("idSession") Long idS) {
        Participant p = userService.deleteParticipantSessions(idP,idS);
        if (p instanceof ParticipantNational) {
            return new ResponseEntity<>((ParticipantNational)p,HttpStatus.OK);
        }
        else if( p instanceof ParticipantInternational) {
            return new ResponseEntity<>((ParticipantInternational) p, HttpStatus.OK);
        }
        else {
            return null;
        }
    }
}
