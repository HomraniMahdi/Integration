package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConventionForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("nomEntreprise")
    private String nomEntreprise;
    private String periodeStage;
    private String nom;
    private String prenom;
    private Integer cin;
    private boolean refuse;
    @Column(name = "valide", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean valide = false;

}
