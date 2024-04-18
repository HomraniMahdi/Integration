package tn.esprit.devminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Offre;
import tn.esprit.devminds.Repository.OffreRepository;

import java.util.List;
@Service
public class OffreService implements IOfferService{
    @Autowired
    OffreRepository offreRepository;

    @Override
    public List<Offre> getAllOffres() {
        return (List<Offre>) offreRepository.findAll();
    }

    @Override
    public Offre getOffreById(Long id) {
        return offreRepository.findById(id).orElse(null);
    }

    @Override
    public Offre addOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre updateOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public void deleteOffre(Long id) {
        offreRepository.deleteById(id);
    }
}
