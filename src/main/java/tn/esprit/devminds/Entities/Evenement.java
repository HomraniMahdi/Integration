package tn.esprit.devminds.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idEvenement;
    String titre;
    String description;
    Date dateEvt;
    String salle;
    @ManyToOne
    Administration administration;
    @ManyToMany(mappedBy = "evenement",cascade = CascadeType.ALL)
    Set<Etudiant> etudiants;
    // Ajoutez ces deux nouveaux champs
    @Column(columnDefinition = "int default 0") // Définit une valeur par défaut de 0 pour le champ
    private int likes;
    private String url;

    @Column(columnDefinition = "int default 0") // Définit une valeur par défaut de 0 pour le champ
    private int dislikes;
    private double price ;
    private int nbparticipants;

}