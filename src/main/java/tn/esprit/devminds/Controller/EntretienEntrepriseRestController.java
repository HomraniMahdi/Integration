package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Entities.EntretienEntreprise;
import tn.esprit.devminds.Service.IEntretienEntreprise;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/entretienEntreprise")
public class EntretienEntrepriseRestController {

    @Autowired
    IEntretienEntreprise iEntretienService;

    @GetMapping
    public List<EntretienEntreprise> getAllEntretiens() {
        return iEntretienService.getAllEntretiens(); // Call the method on the instance of EntretienService
    }

    @GetMapping("/{id}")
    public EntretienEntreprise getEntretienById(@PathVariable Long id) {
        return iEntretienService.getEntretienById(id);
    }

    @PostMapping
    public EntretienEntreprise addEntretien(@RequestBody EntretienEntreprise entretien) {
        return iEntretienService.addEntretien(entretien);
    }

    @PutMapping("/{id}")
    public EntretienEntreprise updateEntretien(@PathVariable Long id, @RequestBody EntretienEntreprise entretien) {
        entretien.setIdEntretien(id);
        return iEntretienService.updateEntretien(entretien);
    }

    @DeleteMapping("/{id}")
    public void deleteEntretien(@PathVariable Long id) {
        iEntretienService.deleteEntretien(id);
    }

}
