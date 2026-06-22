package org.psychohelp.psychohelp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "choixmultiples")
public class ChoixMultiple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String choix ;
    private int score ;


    @ManyToOne
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    @JsonBackReference
    private QuestionsTest questionTest;
}
