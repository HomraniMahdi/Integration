package tn.esprit.devminds.Controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Service.StageRequestService;

@RestController
@RequestMapping("/demande-stage")
@CrossOrigin(origins = "http://localhost:4200")
public class StageRequestController {
    @Autowired
    private StageRequestService stageRequestService;

    @GetMapping("/pdf/{cin}")
    public ResponseEntity<ByteArrayResource> generateDemandeDeStagePdf(@PathVariable Integer cin) {
        try {
            System.out.println("Request received for PDF generation. Etudiant CIN: " + cin);
            Etudiant etudiant = stageRequestService.getEtudiantByCin(cin);
            byte[] pdfContent = stageRequestService.generateDemandeDeStagePdf(etudiant);

            ByteArrayResource resource = new ByteArrayResource(pdfContent);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=demande-de-stage.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(pdfContent.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}