package tn.esprit.devminds.Service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devminds.Entities.Etudiant;
import tn.esprit.devminds.Entities.PdfGenerator;

import java.io.IOException;

@Service
public class StageRequestService {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private PdfStorageService pdfStorageService;

    public Etudiant getEtudiantByCin(Integer cin) {
        return etudiantService.getEtudiantByCin(cin);
    }

    public byte[] generateDemandeDeStagePdf(Etudiant etudiant) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Etudiant not found");
        }

        try {
            byte[] pdfContent = PdfGenerator.generateDemandeDeStagePdf(
                    etudiant.getNom(),
                    etudiant.getPrenom(),
                    etudiant.getCin(),
                    etudiant.getNumero(),
                    etudiant.getEmail()
            );

            // Mettez à jour l'étudiant après la génération du PDF
            etudiant.setDemandeImportee(true);
            etudiant.setDemandeStageEffectuee(true);
            etudiantService.updateEtudiant(etudiant);

            pdfStorageService.savePdf(etudiant.getId(), pdfContent);
            return pdfContent;
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
