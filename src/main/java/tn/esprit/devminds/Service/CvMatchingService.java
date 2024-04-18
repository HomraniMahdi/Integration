package tn.esprit.devminds.Service;

import org.springframework.stereotype.Service;
import tn.esprit.devminds.Config.CvMatchResult;
import tn.esprit.devminds.Entities.Candidature;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CvMatchingService {

    public List<CvMatchResult> matchCvs(List<Candidature> candidatures, Candidature candidatureReference) {
        List<CvMatchResult> matchResults = new ArrayList<>();
        for (Candidature candidature : candidatures) {
            double matchingScore = calculateMatchingScore(candidatureReference, candidature);
            CvMatchResult matchResult = new CvMatchResult(candidature.getIdCandidature(), matchingScore);

            matchResults.add(matchResult);
        }
        matchResults.sort(Comparator.comparing(CvMatchResult::getMatchingScore).reversed());

        return matchResults;
    }

    private double calculateMatchingScore(Candidature candidatureReference, Candidature candidature) {
        double score = 0.0;

        // Comparaison des compétences
        if (candidatureReference.getCompetence() != null && candidature.getCompetence() != null) {
            if (candidatureReference.getCompetence().equalsIgnoreCase(candidature.getCompetence())) {
                score += 50;
            }
        }

        // Comparaison de la durée d'expérience
        if (candidatureReference.getExperienceDu() != null && candidatureReference.getExperienceAu() != null &&
                candidature.getExperienceDu() != null && candidature.getExperienceAu() != null) {

            long diffCandidatureReference = candidatureReference.getExperienceAu().getTime() - candidatureReference.getExperienceDu().getTime();
            long diffCandidature = candidature.getExperienceAu().getTime() - candidature.getExperienceDu().getTime();

            // Convertir la différence en années
            int yearsCandidatureReference = (int) (diffCandidatureReference / (1000 * 60 * 60 * 24 * 365));
            int yearsCandidature = (int) (diffCandidature / (1000 * 60 * 60 * 24 * 365));

            // Comparer la durée d'expérience en années
            if (yearsCandidatureReference >= yearsCandidature) {
                score += 30;
            }
        }

        return score;
    }

}
