package tn.esprit.devminds.Entities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfGenerator {

    public static byte[] generateDemandeDeStagePdf(String nom, String prenom, int cin, int numero, String email) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // Création d'un flux de sortie pour stocker le contenu du PDF
        PdfWriter.getInstance(document, outputStream); // Spécification du flux de sortie pour le document PDF
        document.open();
        Image logo = Image.getInstance("src/main/Logo_ESPRIT_Ariana.jpg");
        logo.scaleAbsolute(100, 100);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Ajout de la date
        Paragraph dateParagraph = new Paragraph("Tunis, le : " + getCurrentDate());
        dateParagraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(dateParagraph);

        // Espacement
        document.add(new Paragraph("\n"));

        // Titre
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
        Paragraph title = new Paragraph("A l’aimable attention de la Direction Générale\nObjet: Demande de Stage\n\n", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Contenu
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        Paragraph content = new Paragraph(
                "Madame, Monsieur,\n\n" +
                        "L’Ecole Supérieure Privée d’Ingénierie et de Technologies, ESPRIT SA, est un établissement d’enseignement supérieur privé ayant pour objet principal, la formation d’ingénieurs dans les domaines des technologies de l’information et de la communication.\n\n" +
                        "Notre objectif consiste à former des ingénieurs opérationnels au terme de leur formation.\n\n" +
                        "Dès lors, nous encourageons nos élèves à mettre en pratique le savoir et les compétences qu’ils ont acquis au cours de leur cursus universitaire.\n\n" +
                        "C’est également dans le but de les amener à s’intégrer dans l’environnement de l’entreprise que nous vous demandons de bien vouloir accepter :\n\n\n" +
                        "L’étudiant(e) :  " + nom + " " + prenom + "\n" +
                        "CIN: " + cin + "\n" +
                        "Numéro: " + numero + "\n" +
                        "Email: " + email + "\n\n" +
                        "Pour effectuer un stage obligatoire, au sein de votre honorable société.\n\n" +
                        "Nous restons à votre entière disposition pour tout renseignement complémentaire.\n\n" +
                        "En vous remerciant pour votre précieux soutien, nous vous prions d’agréer, Madame, Monsieur, l’expression de nos salutations distinguées.\n"
                , contentFont);
        document.add(content);
        Image image = Image.getInstance("src/main/siganture2.png");
        image.scaleAbsolute(100, 100);
        image.setAlignment(Element.ALIGN_RIGHT);
        document.add(image);

        document.close();

        // Retourne le contenu du document PDF en tant que tableau de bytes
        return outputStream.toByteArray();
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

}

