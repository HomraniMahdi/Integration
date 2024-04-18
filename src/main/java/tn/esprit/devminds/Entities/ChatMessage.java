package tn.esprit.devminds.Entities;

import lombok.*;
import jakarta.persistence.*;


import java.awt.*;
import java.io.Serializable;
import java.util.List;



@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity

public class ChatMessage  implements Serializable{
    @Id
     String message;
     String user;
    @ElementCollection
    private List<String> emojis; // Liste des emojis associés au message
    @ElementCollection
    private List<String> reactions; // Liste des réactions associées au message



}
