package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entreprise implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long reference;
    String label;
    String email;
    String nom;
    String adresse;
    @OneToMany(mappedBy = "entreprise")
    Set<DocStage> docStages;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Candidature> candidatures;
    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    Set<Entretien> entretiens;
    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    Set<Offre> offres;
    @OneToMany (mappedBy = "entreprise",cascade = CascadeType.ALL)
    Set<messagerie> messageries;

}