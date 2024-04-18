package tn.esprit.devminds.Entities;

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
public class Administration implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Long idAdmin;
    String signature;
    String label;
    @OneToMany(mappedBy = "administration",cascade = CascadeType.ALL)
    Set<Evenement> Evenements;






}
