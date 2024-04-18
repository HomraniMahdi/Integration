package tn.esprit.devminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Repository.EntretienRepository;

import java.util.List;
@Service
public class EntretienService implements IEntretienService {
    @Autowired
    EntretienRepository entretienRepository;

    @Override
    public List<Entretien> getAllEntretiens() {
        return (List<Entretien>) entretienRepository.findAll();
    }

    @Override
    public Entretien getEntretienById(Long id) {
        return entretienRepository.findById(id).orElse(null);
    }

    @Override
    public Entretien addEntretien(Entretien entretien) {
        return entretienRepository.save(entretien);
    }

    @Override
    public Entretien updateEntretien(Entretien entretien) {
        return entretienRepository.save(entretien);
    }

    @Override
    public void deleteEntretien(Long id) {
        entretienRepository.deleteById(id);
    }




}
