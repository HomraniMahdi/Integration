package tn.esprit.devminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Entreprise;
import tn.esprit.devminds.Repository.EntrepriseRepository;

import java.util.List;

@Service
public class    EntrepriseService implements IEntrepriseService{


    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Override
    public List<Entreprise> getAllEntreprises() {
        return (List<Entreprise>) entrepriseRepository.findAll();
    }

    @Override
    public Entreprise getEntrepriseById(long id) {
        return entrepriseRepository.findById(id).orElse(null);
    }

    @Override
    public Entreprise addEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void deleteEntreprise(long id) {
        entrepriseRepository.deleteById(id);
    }
}
