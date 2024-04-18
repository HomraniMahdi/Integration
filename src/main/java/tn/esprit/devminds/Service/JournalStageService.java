package tn.esprit.devminds.Service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@Slf4j
public class JournalStageService {

    public byte[] generateJournalStageDocument(Long cin) {
        log.info("Génération du journal de stage pour le CIN : {}", cin);

        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Journal de Stage pour le CIN : " + cin);
            run.addBreak();
            run.setText("Contenu: ");
            run.addBreak();
            run.setText("Nom de l'étudiant: ");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du document Word", e);
        }
    }
}
