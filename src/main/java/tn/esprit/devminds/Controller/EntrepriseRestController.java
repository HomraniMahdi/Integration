package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Entreprise;
import tn.esprit.devminds.Service.IEntrepriseService;


import java.util.List;
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/Entreprise")
public class EntrepriseRestController {

    @Autowired
    IEntrepriseService iEntrepriseService;

    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return iEntrepriseService.getAllEntreprises();
    }

    @GetMapping("/{id}")
    public Entreprise getEntrepriseById(@PathVariable long id) {
        return iEntrepriseService.getEntrepriseById(id);
    }

    @PostMapping
    public Entreprise addEntreprise(@RequestBody Entreprise entreprise) {
        return iEntrepriseService.addEntreprise(entreprise);
    }

    @PutMapping("/{id}")
    public Entreprise updateEntreprise(@PathVariable long id, @RequestBody Entreprise entreprise) {
        entreprise.setReference(id);
        return iEntrepriseService.updateEntreprise(entreprise);
    }

    @DeleteMapping("/{id}")
    public void deleteEntreprise(@PathVariable long id) {
        iEntrepriseService.deleteEntreprise(id);
    }




}
