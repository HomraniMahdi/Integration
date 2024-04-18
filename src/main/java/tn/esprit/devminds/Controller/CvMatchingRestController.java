package tn.esprit.devminds.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.devminds.Config.CvMatchResult;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Service.CvMatchingService;


import java.util.List;

@RestController
public class CvMatchingRestController {
    @Autowired
    private CvMatchingService cvMatchingService;

    @PostMapping("/match")
    public ResponseEntity<List<CvMatchResult>> matchCvs(@RequestBody List<Candidature> candidatures, @RequestBody Candidature candidatureReference) {
        List<CvMatchResult> matchResults = cvMatchingService.matchCvs(candidatures, candidatureReference);
        return ResponseEntity.ok().body(matchResults);
    }
}
