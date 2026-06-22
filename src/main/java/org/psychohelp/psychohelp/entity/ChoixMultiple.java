package org.psychohelp.psychohelp.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private QuestionsTest questionTest;
}
