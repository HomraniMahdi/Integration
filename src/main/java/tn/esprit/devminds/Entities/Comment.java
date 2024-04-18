package tn.esprit.devminds.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    Date createdAt;
    String postedBy;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    Post post;
}
