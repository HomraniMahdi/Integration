package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idCandidature;
    String image;
    String cin;
    String nom;
    String  prenom;
    @Enumerated(EnumType.STRING)
    Status status;
    private String email;
    private String confirmEmail;
    private String address;
    private String numTel;

    private String titre;
    private String societe;
    private String experienceDescription;
    @Temporal(TemporalType.DATE)
    private Date experienceDu;
    @Temporal(TemporalType.DATE)
    private Date experienceAu;

    private String etablissement;
    private String specialisation;
    private String niveauEtude;
    private String competence;
    @Temporal(TemporalType.DATE)
    private Date formationDu;
    @Temporal(TemporalType.DATE)
    private Date formationAu;

    @JsonIgnore
    @ManyToOne
    Etudiant etudiant;

}
