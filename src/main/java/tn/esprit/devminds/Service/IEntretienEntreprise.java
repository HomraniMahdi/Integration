package tn.esprit.devminds.Service;

import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Entities.EntretienEntreprise;

import java.util.List;

public interface IEntretienEntreprise {
    List<EntretienEntreprise> getAllEntretiens();
    EntretienEntreprise getEntretienById(Long id);
    EntretienEntreprise addEntretien(EntretienEntreprise entretien);
    EntretienEntreprise updateEntretien(EntretienEntreprise entretien);
    void deleteEntretien(Long id);
}
