package tn.esprit.devminds.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    String name;
    @Column(length = 5000)
    String content;
    String postedBy;
    String img;
    Date date;
    int likeCount;
    int viewCount;
    @ElementCollection
    List<String> tags;


}
