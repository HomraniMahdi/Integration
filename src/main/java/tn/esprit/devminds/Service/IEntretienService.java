package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Entretien;

import java.util.List;

public interface IEntretienService {
    List<Entretien> getAllEntretiens();
    Entretien getEntretienById(Long id);
    Entretien addEntretien(Entretien entretien);
    Entretien updateEntretien(Entretien entretien);
    void deleteEntretien(Long id);
}
