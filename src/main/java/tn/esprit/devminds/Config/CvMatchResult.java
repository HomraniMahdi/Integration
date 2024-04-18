package tn.esprit.devminds.Config;
public class CvMatchResult {
    private Long candidatureId;
    private double matchingScore;

    // Constructeur
    public CvMatchResult(Long candidatureId, double matchingScore) {
        this.candidatureId = candidatureId;
        this.matchingScore = matchingScore;
    }

    // Getters et setters
    public Long getCandidatureId() {
        return candidatureId;
    }

    public void setCandidatureId(Long candidatureId) {
        this.candidatureId = candidatureId;
    }

    public double getMatchingScore() {
        return matchingScore;
    }

    public void setMatchingScore(double matchingScore) {
        this.matchingScore = matchingScore;
    }
}
