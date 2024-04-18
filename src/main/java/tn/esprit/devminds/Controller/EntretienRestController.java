package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Service.IEntretienService;


import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/entretien")
public class EntretienRestController {

    @Autowired
    IEntretienService iEntretienService;

    @GetMapping
    public List<Entretien> getAllEntretiens() {
        return iEntretienService.getAllEntretiens(); // Call the method on the instance of EntretienService
    }

    @GetMapping("/{id}")
    public Entretien getEntretienById(@PathVariable Long id) {
        return iEntretienService.getEntretienById(id);
    }

    @PostMapping
    public Entretien addEntretien(@RequestBody Entretien entretien) {
        return iEntretienService.addEntretien(entretien);
    }

    @PutMapping("/{id}")
    public Entretien updateEntretien(@PathVariable Long id, @RequestBody Entretien entretien) {
        entretien.setIdEntretien(id);
        return iEntretienService.updateEntretien(entretien);
    }

    @DeleteMapping("/{id}")
    public void deleteEntretien(@PathVariable Long id) {
        iEntretienService.deleteEntretien(id);
    }

}
