package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.psychohelp.psychohelp.dto.ChoixMultiplesReponseDTO;

@Data
@Entity
@Table(name = "choixmultiples")
public class ChoixMultiple extends ChoixMultiplesReponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String choix ;
    private int score ;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    @JsonBackReference
    private QuestionsTest questionTest;
}
