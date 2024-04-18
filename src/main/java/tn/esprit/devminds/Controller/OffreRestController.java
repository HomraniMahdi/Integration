package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Offre;
import tn.esprit.devminds.Service.IOfferService;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/offres")
public class OffreRestController {


    @Autowired
    IOfferService iOfferService;


    @GetMapping
    public ResponseEntity<List<Offre>> getAllOffres() {
        List<Offre> offres = iOfferService.getAllOffres();
        return ResponseEntity.ok().body(offres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offre> getOffreById(@PathVariable Long id) {
        Offre offre = iOfferService.getOffreById(id);
        return ResponseEntity.ok().body(offre);
    }

    @PostMapping
    public ResponseEntity<Offre> addOffre(@RequestBody Offre offre) {
        Offre newOffre = iOfferService.addOffre(offre);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOffre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable Long id, @RequestBody Offre offre) {
        offre.setIdOffre(id);
        Offre updatedOffre = iOfferService.updateOffre(offre);
        return ResponseEntity.ok().body(updatedOffre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        iOfferService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }
}
