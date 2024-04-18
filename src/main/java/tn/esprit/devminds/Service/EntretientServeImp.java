package tn.esprit.devminds.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Candidature;
import tn.esprit.devminds.Entities.Entretien;
import tn.esprit.devminds.Repository.ICandidatureRepo;
import tn.esprit.devminds.Repository.IEntretientRepo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EntretientServeImp implements IEntretient{
    private IEntretientRepo iEntretientRepo;
    private ICandidatureRepo iCandidatureRepo;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Entretien addEntretien(Entretien e) {
        return iEntretientRepo.save(e);
    }

    @Override
    public Entretien updateEntretien(Entretien e) {
        return iEntretientRepo.save(e);
    }

    @Override
    public List<Entretien> getAllEntretien() {
        return iEntretientRepo.findAll();
    }

    @Override
    public Entretien getEntreById(Long idEntretien) {
        return iEntretientRepo.findById(idEntretien).orElse(null);
    }

    @Override
    public void deleteEntretien(Long idEntretien) {
    iEntretientRepo.deleteById(idEntretien);
    }

    @Override
    public Entretien affecterEntretienToCandidature(Long idCandidature, Entretien entretien) {
        Optional<Candidature> optionalCandidature = iCandidatureRepo.findById(idCandidature);
        if (optionalCandidature.isPresent() && entretien != null) {
            Candidature candidature = optionalCandidature.get();
            entretien.setCandidature(candidature);
            entretien = iEntretientRepo.save(entretien);
            return entretien;
        } else {
            return null;
        }
    }

    @Override
    public void sendEmail(Entretien entretien) {
        String subject = "Entretien pour la candidature";
        String body = "Détails de l'entretien:\n" +
                "Date: " + entretien.getDateEntretien() + "\n" +
                "Heure: " + entretien.getHeure() + "\n" +
                "Lien: " + entretien.getLien();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hammoudihayfa93@gmail.com");
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);

    }
}
