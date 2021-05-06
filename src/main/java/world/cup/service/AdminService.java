package world.cup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.cup.exception.ResourceNotFoundException;
import world.cup.models.Pays;
import world.cup.repositories.PaysRepository;

import java.util.List;

@Service
public class AdminService {
    private PaysRepository paysRepository;

    @Autowired
    public AdminService(PaysRepository paysRepository) {
        this.paysRepository=paysRepository;
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

}
