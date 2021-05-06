package world.cup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import world.cup.models.Pays;
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
}
