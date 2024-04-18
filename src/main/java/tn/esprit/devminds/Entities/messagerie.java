package tn.esprit.devminds.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class messagerie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idMesg;
    String content;
    @Temporal(TemporalType.DATE)
    Date dateMesg;
    String sender;
    String recipient;
    @Enumerated(EnumType.STRING)
    MessageStatus status;
    @Enumerated(EnumType.STRING)
    MessageType type;
    @JsonIgnore
    @ManyToOne
    Entreprise entreprise;
    @JsonIgnore
    @ManyToOne
    Etudiant etudiant;
}
