package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entretien implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idEntretien;
    @Temporal(TemporalType.DATE)
    Date dateEntretien;
    String heure;
    String lien;
    @JsonIgnore
    @ManyToOne
    Entreprise entreprise;
    @JsonIgnore
    @ManyToOne
    Candidature candidature;


}
