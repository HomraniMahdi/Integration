package tn.esprit.devminds.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pdf_entity")
public class PdfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] content;

    // l'ID de l'étudiant associé
    private Long etudiantId;



    public PdfEntity(byte[] content, Long etudiantId) {
        this.content = Arrays.copyOf(content, content.length);
        this.etudiantId = etudiantId;
    }

}
