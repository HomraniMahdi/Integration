package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Entretien;

import java.util.List;

public interface IEntretient {
    public Entretien addEntretien (Entretien e);
    public Entretien updateEntretien(Entretien e);
    public List<Entretien> getAllEntretien();
    public Entretien getEntreById(Long idEntretien);
    public void deleteEntretien(Long idEntretien);
    Entretien affecterEntretienToCandidature(Long idCandidature, Entretien entretien) ;
    public void sendEmail(Entretien entretien);
}
