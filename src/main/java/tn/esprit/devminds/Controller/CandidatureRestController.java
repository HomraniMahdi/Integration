package tn.esprit.devminds.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Service.ICandidature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@AllArgsConstructor
@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CandidatureRestController {
    private ICandidature iCandidature;

    @PostMapping("/addCand")
    public Candidature addCandidature(@RequestBody Candidature c) {
        return iCandidature.addCandidature(c);
    }

    @PutMapping("/updateCand/{idCandidature}")
    public Candidature updateCandidature(@PathVariable long idCandidature, @RequestBody Candidature c) {
        return iCandidature.updateCandidature(idCandidature, c);
    }

    @GetMapping("/getAllCand")
    public List<Candidature> getAllCandidature() {
        return iCandidature.getAllCandidature();
    }

    @GetMapping("/getCandById/{idCandidature}")
    public Candidature getCandidatureById(@PathVariable Long idCandidature) {
        return iCandidature.getCandidatureById(idCandidature);
    }

    @DeleteMapping("/deleteCand/{idCandidature}")
    public void deleteCandidture(@PathVariable long idCandidature) {
        iCandidature.deleteCandidture(idCandidature);
    }

    @GetMapping("/getCandByFavorableStatus")
    public List<Candidature> getCandidaturesByFavorableStatus() {
        return iCandidature.getCandidaturesByFavorableStatus();
    }

    @PostMapping(value = "/addWithFile", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        log.info("Handling file upload: {}", file.getOriginalFilename());

        try {
            String destinationFolder = "C:\\Users\\dell\\Desktop\\tele";
            File destinationDir = new File(destinationFolder);

            if (!destinationDir.exists()) {
                destinationDir.mkdirs();
            }

            Path filePath = Paths.get(destinationFolder, file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            var result = Map.of(
                    "filename", file.getOriginalFilename(),
                    "fileUri", fileUri
            );

            return ResponseEntity.ok().body(result);
        } catch (IOException e) {
            log.error("Error uploading file: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
